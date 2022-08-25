package com.gmail.pzalejko.cdc.kafkastreamsdemo.processor;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import postgres.demo.account.Envelope;

@Component
public class WordCountProcessor {

    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<GenericRecord, Envelope> messageStream = streamsBuilder
                .stream("postgres.demo.account");

        messageStream
                .peek(new ForeachAction<GenericRecord, Envelope>() {
                    @Override
                    public void apply(GenericRecord key, Envelope value) {
                        System.out.println(value);
                    }
                })
                .to("output-topic");
    }
}