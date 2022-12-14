package com.gmail.pzalejko.cdc.demo.account.domain;

import com.gmail.pzalejko.cdc.demo.config.KafkaTopicConfiguration;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class MoneyTransferService {

    private final AccountRepository accountRepository;
    private final ApplicationEventPublisher applicationEventPublisher;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    // @Transactional
    //@Transactional("chainedKafkaJpaTransactionManager")


    public void sendMoney(@NonNull SendMoneyDto dto) {
        var from = accountRepository.findById(dto.from).orElseThrow();
        var to = accountRepository.findById(dto.to).orElseThrow();
        var value = BigDecimal.valueOf(dto.value); // could be a value object, but it's demo, KISS...
        var timestamp = Instant.now();

        from.withdrawTo(to, value, timestamp);
        to.depositFrom(from, value, timestamp);

        accountRepository.save(from);
        accountRepository.save(to);

        var moneyTransferredEvent = new MoneyTransferredEvent(from.getId(), to.getId(), value.doubleValue(), timestamp);
        var fromBalanceChangedEvent = new AccountBalanceChangedEvent(from.getId(), from.getBalance().doubleValue(), timestamp);
        var toBalanceChangedEvent = new AccountBalanceChangedEvent(to.getId(), to.getBalance().doubleValue(), timestamp);

        kafkaTemplate.send(KafkaTopicConfiguration.MONEY_TRANSFERRED_TOPIC, Long.toString(moneyTransferredEvent.from()), moneyTransferredEvent);
        kafkaTemplate.send(KafkaTopicConfiguration.ACCOUNT_BALANCE_CHANGED_TOPIC, Long.toString(fromBalanceChangedEvent.accountId()), fromBalanceChangedEvent);
        kafkaTemplate.send(KafkaTopicConfiguration.ACCOUNT_BALANCE_CHANGED_TOPIC, Long.toString(toBalanceChangedEvent.accountId()), toBalanceChangedEvent);


//        applicationEventPublisher.publishEvent(moneyTransferredEvent);
//        applicationEventPublisher.publishEvent(fromBalanceChangedEvent);
//        applicationEventPublisher.publishEvent(toBalanceChangedEvent);

        // throw new RuntimeException();
    }

    public record MoneyTransferredEvent(long from, long to, double value, Instant when) {
    }


    public record AccountBalanceChangedEvent(long accountId, double value, Instant when) {
    }

    public record SendMoneyDto(long from, long to, double value) {
    }
}
