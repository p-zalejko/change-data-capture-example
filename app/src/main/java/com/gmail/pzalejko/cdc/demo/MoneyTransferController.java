package com.gmail.pzalejko.cdc.demo;

import com.gmail.pzalejko.cdc.demo.config.KafkaTopicConfiguration;
import com.gmail.pzalejko.cdc.demo.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.Instant;

@RestController
@RequiredArgsConstructor
public class MoneyTransferController {

    private final AccountRepository accountRepository;
    private final KafkaTemplate<String, Object> kafkaTemplate;

    @PostMapping("/accounts/{clientId}/{id}/sendMoney")
    @Transactional("chainedKafkaJpaTransactionManager")
    void sendMoney(@PathVariable long id, @RequestBody SendMoneyDto dto) {
        var from = accountRepository.findById(id).orElseThrow();
        var to = accountRepository.findById(dto.to).orElseThrow();
        var value = BigDecimal.valueOf(dto.value);
        var timestamp = Instant.now();

        from.withdrawTo(to, value, timestamp);
        to.depositFrom(from, value, timestamp);

        accountRepository.save(from);
        accountRepository.save(to);

        var moneyTransferredEvent = new MoneyTransferredEvent(from.getId(), to.getId(), dto.value, timestamp);
        var fromBalanceChanged = new AccountBalanceChangedEvent(from.getId(), from.getBalance().doubleValue(), timestamp);
        var toBalanceChanged = new AccountBalanceChangedEvent(to.getId(), to.getBalance().doubleValue(), timestamp);

        kafkaTemplate.send(KafkaTopicConfiguration.MONEY_TRANSFERRED_TOPIC, Long.toString(id), moneyTransferredEvent);
        kafkaTemplate.send(KafkaTopicConfiguration.ACCOUNT_BALANCE_CHANGED_TOPIC, Long.toString(from.getId()), fromBalanceChanged);
        kafkaTemplate.send(KafkaTopicConfiguration.ACCOUNT_BALANCE_CHANGED_TOPIC, Long.toString(to.getId()), toBalanceChanged);

        // throw new IllegalArgumentException();
    }

    record MoneyTransferredEvent(long from, long to, double value, Instant when) {
    }

    record AccountBalanceChangedEvent(long accountId, double value, Instant when) {
    }


    record SendMoneyDto(long to, double value) {
    }


}
