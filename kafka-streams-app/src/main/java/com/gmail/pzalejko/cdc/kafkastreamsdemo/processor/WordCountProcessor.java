package com.gmail.pzalejko.cdc.kafkastreamsdemo.processor;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordCountProcessor {

    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> messageStream = streamsBuilder
                .stream("moneyTransferred", Consumed.with(STRING_SERDE, STRING_SERDE));

        messageStream
                .peek(new ForeachAction<String, String>() {
                    @Override
                    public void apply(String key, String value) {
                        System.out.println(value);
                    }
                })
                .to("output-topic");
    }
}