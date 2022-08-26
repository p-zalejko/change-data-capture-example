package com.gmail.pzalejko.cdc.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

    public static final String MONEY_TRANSFERRED_TOPIC = "demo.moneyTransferred";
    public static final String ACCOUNT_BALANCE_CHANGED_TOPIC = "demo.accountBalanceChanged";
    @Bean
    NewTopic moneyTransferredTopic() {
        return TopicBuilder.name(MONEY_TRANSFERRED_TOPIC)
                .partitions(5)
                .replicas(1)
                .build();
    }

    @Bean
    NewTopic accountBalanceChangedTopic() {
        return TopicBuilder.name(ACCOUNT_BALANCE_CHANGED_TOPIC)
                .partitions(5)
                .replicas(1)
                .build();
    }
}