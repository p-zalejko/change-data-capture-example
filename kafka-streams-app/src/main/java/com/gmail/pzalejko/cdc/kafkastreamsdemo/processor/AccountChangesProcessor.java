package com.gmail.pzalejko.cdc.kafkastreamsdemo.processor;

import com.gmail.pzalejko.cdc.AccountAggregate;
import com.gmail.pzalejko.cdc.kafkastreamsdemo.config.KafkaStreamSerdeFactory;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

        builder.stream("postgres.demo.account", Consumed.with(accountKeySerde, accountSerde))
                .selectKey((key, value) -> key.getId()) // value.getAfter().getAccountOwnerId()
                .to("postgres.demo.account-ks", Produced.with(longKeySerde, accountSerde));

        builder.stream("postgres.demo.account_owner", Consumed.with(accountOwnerKeySerde, accountOwnerSerde))
                .selectKey((key, value) -> key.getId())
                .to("postgres.demo.account-owner-ks", Produced.with(longKeySerde, accountOwnerSerde));

        KTable<Long, postgres.demo.account.Envelope> accounts = builder.table(
                "postgres.demo.account-ks",
                Consumed.with(longKeySerde, accountSerde)
        );
        KTable<Long, postgres.demo.account_owner.Envelope> accountOwners = builder.table(
                "postgres.demo.account-owner-ks",
                Consumed.with(longKeySerde, accountOwnerSerde)
        );

        // join a table and a table with a foreign key
        // https://developer.confluent.io/tutorials/foreign-key-joins/kstreams.html
        // https://www.confluent.io/blog/data-enrichment-with-kafka-streams-foreign-key-joins/
        final AccountAggregateJoiner joiner = new AccountAggregateJoiner();
        final Function<postgres.demo.account.Envelope, Long> getAlbumId = a -> a.getAfter().getAccountOwnerId();
        KTable<Long, AccountAggregate> aggregatedData = accounts
                .join(accountOwners, getAlbumId, joiner);

        aggregatedData.toStream()
                .peek((key, value) -> log.info("Aggregated: {}", value))
                .to("accountState", Produced.with(longKeySerde, accountAggregateSerde));
    }

    static class AccountAggregateJoiner implements ValueJoiner<postgres.demo.account.Envelope, postgres.demo.account_owner.Envelope, AccountAggregate> {

        @Override
        public AccountAggregate apply(postgres.demo.account.Envelope value1, postgres.demo.account_owner.Envelope value2) {
            return AccountAggregate.newBuilder()
                    .setBalance(value1.getAfter().getBalance().doubleValue())
                    .setName(value2.getAfter().getName())
                    .setAccountOwnerId(value1.getAfter().getAccountOwnerId())
                    .setAccountId(value1.getAfter().getId())
                    .build();
        }
    }
}