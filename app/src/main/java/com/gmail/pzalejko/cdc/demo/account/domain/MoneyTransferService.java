package com.gmail.pzalejko.cdc.demo.account.domain;

import com.gmail.pzalejko.cdc.demo.cdc.AccountStateCdcService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;

@Service
@RequiredArgsConstructor
public class MoneyTransferService {

    private final AccountRepository accountRepository;
    private final AccountStateCdcService accountStateCdcService;

    @Transactional("transactionManager")
    public void sendMoney(@NonNull SendMoneyDto dto) {
        var from = accountRepository.findById(dto.from).orElseThrow();
        var to = accountRepository.findById(dto.to).orElseThrow();
        var value = BigDecimal.valueOf(dto.value); // could be a value object, but it's demo, KISS...
        var timestamp = Instant.now();

        from.withdrawTo(to, value, timestamp);
        to.depositFrom(from, value, timestamp);

        accountRepository.save(from);
        accountRepository.save(to);
        accountStateCdcService.updateStateCdc(from);
        accountStateCdcService.updateStateCdc(to);

        // throw new RuntimeException();
    }

    public record MoneyTransferredEvent(long from, long to, double value, Instant when) {
    }


    public record AccountBalanceChangedEvent(long accountId, double value, Instant when) {
    }

    public record SendMoneyDto(long from, long to, double value) {
    }
}
