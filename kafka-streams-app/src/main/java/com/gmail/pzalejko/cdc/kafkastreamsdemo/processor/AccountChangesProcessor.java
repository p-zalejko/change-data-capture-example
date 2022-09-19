package com.gmail.pzalejko.cdc.kafkastreamsdemo.processor;

import com.gmail.pzalejko.cdc.AccountAggregate;
import com.gmail.pzalejko.cdc.kafkastreamsdemo.config.KafkaStreamSerdeFactory;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import postgres.demo.account.Envelope;
import postgres.demo.account.Key;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
@Slf4j
public class AccountChangesProcessor {

    private final KafkaStreamSerdeFactory serdeFactory;

    @Autowired
    void buildPipeline(StreamsBuilder builder) {
        Serde<Long> longKeySerde = serdeFactory.getPrimitiveAvroSerde(true);
        SpecificAvroSerde<postgres.demo.account.Envelope> accountSerde = serdeFactory.getSpecificAvroSerde();
        SpecificAvroSerde<postgres.demo.account.Key> accountKeySerde = serdeFactory.getSpecificAvroSerde();
        SpecificAvroSerde<postgres.demo.account_owner.Envelope> accountOwnerSerde = serdeFactory.getSpecificAvroSerde();
        SpecificAvroSerde<postgres.demo.account_owner.Key> accountOwnerKeySerde = serdeFactory.getSpecificAvroSerde();
        SpecificAvroSerde<AccountAggregate> accountAggregateSerde = serdeFactory.getSpecificAvroSerde();

        KTable<Long, postgres.demo.account.Envelope> accounts = builder
                .stream(
                        "postgres.demo.account",
                        Consumed.with(accountKeySerde, accountSerde)
                ).map(new KeyValueMapper<Key, Envelope, KeyValue<Long, Envelope>>() {
                    @Override
                    public KeyValue<Long, Envelope> apply(Key key, Envelope value) {
                        return KeyValue.pair(key.getId(), value); // we could change the key to have the same...  value.getAfter().getAccountOwnerId()
                    }
                })
                .toTable(Materialized.with(longKeySerde, accountSerde));


        KTable<Long, postgres.demo.account_owner.Envelope> accountOwners = builder
                .stream(
                        "postgres.demo.account_owner",
                        Consumed.with(accountOwnerKeySerde, accountOwnerSerde)
                )
                .map(new KeyValueMapper<postgres.demo.account_owner.Key, postgres.demo.account_owner.Envelope, KeyValue<Long, postgres.demo.account_owner.Envelope>>() {
                    @Override
                    public KeyValue<Long, postgres.demo.account_owner.Envelope> apply(postgres.demo.account_owner.Key key, postgres.demo.account_owner.Envelope value) {
                        return KeyValue.pair(key.getId(), value);
                    }
                })
                .toTable(Materialized.with(longKeySerde, accountOwnerSerde));

        // join a table and a table with a foreign key
        // https://developer.confluent.io/tutorials/foreign-key-joins/kstreams.html
        // https://www.confluent.io/blog/data-enrichment-with-kafka-streams-foreign-key-joins/
        final AccountStateJoiner joiner = new AccountStateJoiner();
        final Function<postgres.demo.account.Envelope, Long> getAlbumId = a -> a.getAfter().getAccountOwnerId();
        KTable<Long, AccountAggregate> aggregatedData = accounts
                .join(accountOwners, getAlbumId, joiner);

        aggregatedData.toStream()
                .peek((key, value) -> log.info("Account balance captured: {}", value))
                .to("demo.ks.accountBalanceChanged", Produced.with(longKeySerde, accountAggregateSerde));
    }

    static class AccountStateJoiner implements ValueJoiner<postgres.demo.account.Envelope, postgres.demo.account_owner.Envelope, AccountAggregate> {

        @Override
        public AccountAggregate apply(postgres.demo.account.Envelope value1, postgres.demo.account_owner.Envelope value2) {
            return AccountAggregate.newBuilder()
                    .setBalance(value1.getAfter().getBalance().doubleValue())
                    .setName(String.format("%s %s", value2.getAfter().getName(), value2.getAfter().getSurname()))
                    .setAccountOwnerId(value1.getAfter().getAccountOwnerId())
                    .setAccountId(value1.getAfter().getId())
                    .build();
        }
    }
}