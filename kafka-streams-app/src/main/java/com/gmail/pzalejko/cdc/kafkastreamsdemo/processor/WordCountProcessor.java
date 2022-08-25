package com.gmail.pzalejko.cdc.kafkastreamsdemo.processor;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.ForeachAction;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.ValueJoiner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import postgres.demo.account.Envelope;

@Component
public class WordCountProcessor {

    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<GenericRecord, Envelope> messageStream = streamsBuilder.stream("postgres.demo.account");

        messageStream
                .peek(new ForeachAction<GenericRecord, Envelope>() {
                    @Override
                    public void apply(GenericRecord key, Envelope value) {
                        System.out.println(value);
                    }
                })
                .to("output-topic");
    }

// https://stackoverflow.com/questions/56939323/kafka-stream-dsl-non-key-join-current-workaround-explained
    // https://www.confluent.io/blog/data-enrichment-with-kafka-streams-foreign-key-joins/
    void ddd(StreamsBuilder builder){
        KStream<String, String> account = builder.stream("postgres.demo.account");
        KStream<String, String> accountOwner = builder.stream("postgres.demo.account_owner");

        ValueJoiner<String, String, String> valueJoiner = (leftValue, rightValue) -> {
            return leftValue + rightValue;
        };
        leftStream.join(rightStream,
                valueJoiner,
                JoinWindows.of(Duration.ofSeconds(10)));


    }
    @StreamListener
    @SendTo(Bindings.RATED_MOVIES)
    KStream<Long, RatedMovie> rateMoviesFor(@Input(Bindings.AVG_TABLE) KTable<Long, Double> ratings,
                                            @Input(Bindings.MOVIES) KTable<Long, Movie> movies) {

        ValueJoiner<Movie, Double, RatedMovie> joiner = (movie, rating) ->
                new RatedMovie(movie.getMovieId(), movie.getReleaseYear(), movie.getTitle(), rating);

        movies
                .join(ratings, joiner, Materialized
                        .<Long, RatedMovie, KeyValueStore<Bytes, byte[]>>as(Bindings.RATED_MOVIES_STORE)
                        .withKeySerde(Serdes.Long())
                        .withValueSerde(new JsonSerde<>(RatedMovie.class)));

        return movies.join(ratings, joiner).toStream();
    }
}