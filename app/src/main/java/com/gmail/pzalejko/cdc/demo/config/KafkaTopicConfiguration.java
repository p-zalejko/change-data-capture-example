package com.gmail.pzalejko.cdc.demo.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

  public static final String MONEY_TRANSFERRED_TOPIC = "moneyTransferred";

  @Bean
  public NewTopic topicExample() {
    return TopicBuilder.name(MONEY_TRANSFERRED_TOPIC)
      .partitions(5)
      .replicas(1)
      .build();
  }
}