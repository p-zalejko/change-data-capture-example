package com.gmail.pzalejko.cdc.kafkastreamsdemo.processor;

import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import postgres.demo.account.Envelope;

import java.time.Instant;

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
    // https://stackoverflow.com/questions/62884230/ktable-ktable-foreign-key-join-not-producing-all-messages-when-topics-have-more
//    https://debezium.io/blog/2021/03/18/understanding-non-key-joins-with-quarkus-extension-for-kafka-streams/

    void ddd(StreamsBuilder builder){
        KStream<String, String> account = builder.stream("postgres.demo.account");
        KStream<String, String> accountOwner = builder.stream("postgres.demo.account_owner");

//        final KTable<String, String> displayProducts = account.join(
//                accountOwner,
//                new KeyValueMapper<String, String, String>(){
//
//
//                    @Override
//                    public String apply(String key, String value) {
//                        return null;
//                    }
//                },
//             null
//        );

///**
// * Join records of this {@code KTable} with another {@code KTable} using non-windowed left join.
// * <p>
// * This is a foreign key join, where the joining key is determined by the {@code foreignKeyExtractor}.
// *
// * @param other               the other {@code KTable} to be joined with this {@code KTable}. Keyed by KO.
// * @param foreignKeyExtractor a {@link Function} that extracts the key (KO) from this table's value (V). If the
// *                            result is null, the update is ignored as invalid.
// * @param joiner              a {@link ValueJoiner} that computes the join result for a pair of matching records
// * @param <VR>                the value type of the result {@code KTable}
// * @param <KO>                the key type of the other {@code KTable}
// * @param <VO>                the value type of the other {@code KTable}
// * @return a {@code KTable} that contains only those records that satisfy the given predicate
// */
//<VR, KO, VO> KTable<K, VR> leftJoin(final KTable<KO, VO> other,
//        final Function<V, KO> foreignKeyExtractor,
//        final ValueJoiner<V, VO, VR> joiner);
//
//


        account.join(
                accountOwner,


        )
//         account.join(
//                accountOwner,
//                String::toLowerCase,
//                (product, displayMerchant) -> Instant.now()
//        );

        displayProducts.toStream().to("DisplayProducts");
//        ValueJoiner<String, String, String> valueJoiner = (leftValue, rightValue) -> {
//            return leftValue + rightValue;
//        };
//        leftStream.join(rightStream,
//                valueJoiner,
//                JoinWindows.of(Duration.ofSeconds(10)));


    }
//    @StreamListener
//    @SendTo(Bindings.RATED_MOVIES)
//    KStream<Long, RatedMovie> rateMoviesFor(@Input(Bindings.AVG_TABLE) KTable<Long, Double> ratings,
//                                            @Input(Bindings.MOVIES) KTable<Long, Movie> movies) {
//
//        ValueJoiner<Movie, Double, RatedMovie> joiner = (movie, rating) ->
//                new RatedMovie(movie.getMovieId(), movie.getReleaseYear(), movie.getTitle(), rating);
//
//        movies
//                .join(ratings, joiner, Materialized
//                        .<Long, RatedMovie, KeyValueStore<Bytes, byte[]>>as(Bindings.RATED_MOVIES_STORE)
//                        .withKeySerde(Serdes.Long())
//                        .withValueSerde(new JsonSerde<>(RatedMovie.class)));
//
//        return movies.join(ratings, joiner).toStream();
//    }
}