package com.gmail.pzalejko.cdc.demo;

import com.gmail.pzalejko.cdc.demo.domain.AccountDetails;
import com.gmail.pzalejko.cdc.demo.domain.AccountDetailsRepository;
import com.gmail.pzalejko.cdc.demo.domain.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class TransferController {

    private final AccountRepository accountRepository;




    @PostMapping("/accounts/{clientId}/{id}/sendMoney")
    void sendMoney(@PathVariable long id, @RequestBody SendMoneyDto dto) {
        var from = accountRepository.findById(id).orElseThrow();
        var to = accountRepository.findById(dto.to).orElseThrow();
        var value = BigDecimal.valueOf(dto.value);

        from.withdrawTo(to, value);
        to.depositFrom(from, value);

        accountRepository.save(from);
        accountRepository.save(to);
    }

    record SendMoneyDto(long to, double value) {
    }

}
