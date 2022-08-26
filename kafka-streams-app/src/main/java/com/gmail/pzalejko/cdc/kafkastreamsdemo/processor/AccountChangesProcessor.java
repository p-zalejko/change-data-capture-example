package com.gmail.pzalejko.cdc.kafkastreamsdemo.processor;

import com.gmail.pzalejko.cdc.AccountAggregate;
import com.gmail.pzalejko.cdc.AccountAggregateKey;
import com.gmail.pzalejko.cdc.kafkastreamsdemo.config.KafkaStreamSerdeFactory;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import lombok.RequiredArgsConstructor;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
@RequiredArgsConstructor
public class AccountChangesProcessor {

    private final KafkaStreamSerdeFactory serdeFactory;

    @Autowired
    void buildPipeline(StreamsBuilder builder) {
//        KStream<GenericRecord, Envelope> messageStream = streamsBuilder.stream("postgres.demo.account");
//
//        messageStream
//                .peek(new ForeachAction<GenericRecord, Envelope>() {
//                    @Override
//                    public void apply(GenericRecord key, Envelope value) {
//                        System.out.println(value);
//                    }
//                })
//                .to("output-topic");


        Serde<GenericRecord> keySerde = serdeFactory.getGenericServer();
        SpecificAvroSerde<postgres.demo.account.Envelope> accountSerde = serdeFactory.getSpecificAvroSerde();
        SpecificAvroSerde<postgres.demo.account_owner.Envelope> accountOwnerSerde = serdeFactory.getSpecificAvroSerde();
        SpecificAvroSerde<AccountAggregate> accountAggregateSerde = serdeFactory.getSpecificAvroSerde();

        KTable<GenericRecord, postgres.demo.account.Envelope> accounts = builder.table(
                "postgres.demo.account",
                Consumed.with(keySerde, accountSerde)
        );
        KTable<GenericRecord, postgres.demo.account_owner.Envelope> accountOwners = builder.table(
                "postgres.demo.account_owner",
                Consumed.with(keySerde, accountOwnerSerde)
        );

        // join a table and a table with a foreign key
        // https://developer.confluent.io/tutorials/foreign-key-joins/kstreams.html
        // https://www.confluent.io/blog/data-enrichment-with-kafka-streams-foreign-key-joins/
        final AccountAggregateJoiner joiner = new AccountAggregateJoiner();
        final Function<postgres.demo.account.Envelope, GenericRecord> getAlbumId = a -> AccountAggregateKey
                .newBuilder()
                .setAccountOwnerId(a.getAfter().getAccountOwnerId())
                .build();
        final KTable<GenericRecord, AccountAggregate> aggregatedData = accounts
                .join(accountOwners, getAlbumId, joiner);

        aggregatedData.toStream()
                .peek(new ForeachAction<GenericRecord, AccountAggregate>() {
                    @Override
                    public void apply(GenericRecord key, AccountAggregate value) {
                        System.out.println(value);
                    }
                })
                .to("accountState",
                        Produced.with(keySerde, accountAggregateSerde)
                );
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

    // https://developer.confluent.io/tutorials/foreign-key-joins/kstreams.html
// https://stackoverflow.com/questions/56939323/kafka-stream-dsl-non-key-join-current-workaround-explained
    // https://www.confluent.io/blog/data-enrichment-with-kafka-streams-foreign-key-joins/
    // https://stackoverflow.com/questions/62884230/ktable-ktable-foreign-key-join-not-producing-all-messages-when-topics-have-more
//    https://debezium.io/blog/2021/03/18/understanding-non-key-joins-with-quarkus-extension-for-kafka-streams/

}