package com.gmail.pzalejko.cdc.demo.spring;

import com.gmail.pzalejko.cdc.demo.account.domain.MoneyTransferService;
import com.gmail.pzalejko.cdc.demo.config.KafkaTopicConfiguration;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@RequiredArgsConstructor
class MoneyTransferredSpringEventConsumer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @TransactionalEventListener
    void consumeMoneyTransferredEvent(MoneyTransferService.MoneyTransferredEvent event) {
        kafkaTemplate.send(KafkaTopicConfiguration.MONEY_TRANSFERRED_TOPIC, Long.toString(event.from()), event);
    }

    @TransactionalEventListener
    void consumeAccountBalanceChangedEvent(MoneyTransferService.AccountBalanceChangedEvent event) {
        kafkaTemplate.send(KafkaTopicConfiguration.ACCOUNT_BALANCE_CHANGED_TOPIC, Long.toString(event.accountId()), event);
    }
}
