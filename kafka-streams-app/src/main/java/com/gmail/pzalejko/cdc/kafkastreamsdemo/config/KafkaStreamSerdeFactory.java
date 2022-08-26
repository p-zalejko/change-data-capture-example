package com.gmail.pzalejko.cdc.kafkastreamsdemo.config;

import io.confluent.kafka.serializers.AbstractKafkaSchemaSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroSerializer;
import io.confluent.kafka.streams.serdes.avro.GenericAvroSerde;
import io.confluent.kafka.streams.serdes.avro.SpecificAvroSerde;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.specific.SpecificRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class KafkaStreamSerdeFactory {

    @Value(value = "${spring.kafka.schema.registry.url}")
    private String schemaRegistryUrl;

    public Serde<GenericRecord> getGenericServer() {
        final GenericAvroSerde specificAvroSerde = new GenericAvroSerde();
        final HashMap<String, String> serdeConfig = new HashMap<>();
        serdeConfig.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
//        serdeConfig.put( AbstractKafkaSchemaSerDeConfig.AUTO_REGISTER_SCHEMAS,  "false");
//        serdeConfig.put( AbstractKafkaSchemaSerDeConfig.USE_LATEST_VERSION, "true");
        specificAvroSerde.configure(serdeConfig, false);

        return specificAvroSerde;
    }

    public <T> Serde<T> getPrimitiveAvroSerde(boolean isKey) {
        final KafkaAvroDeserializer deserializer = new KafkaAvroDeserializer();
        final KafkaAvroSerializer serializer = new KafkaAvroSerializer();
        final Map<String, String> config = new HashMap<>();

        config.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        deserializer.configure(config, isKey);
        serializer.configure(config, isKey);
        return (Serde<T>) Serdes.serdeFrom(serializer, deserializer);
    }

    public <T extends SpecificRecord> SpecificAvroSerde<T> getSpecificAvroSerde() {
        final SpecificAvroSerde<T> specificAvroSerde = new SpecificAvroSerde<>();

        final HashMap<String, String> serdeConfig = new HashMap<>();
        serdeConfig.put(AbstractKafkaSchemaSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
//        serdeConfig.put( AbstractKafkaSchemaSerDeConfig.AUTO_REGISTER_SCHEMAS, "false");
//        serdeConfig.put( AbstractKafkaSchemaSerDeConfig.USE_LATEST_VERSION, "true");
        specificAvroSerde.configure(serdeConfig, false);
        return specificAvroSerde;
    }

}
