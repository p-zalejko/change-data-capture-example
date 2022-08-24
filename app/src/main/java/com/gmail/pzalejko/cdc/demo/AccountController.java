package com.gmail.pzalejko.cdc.demo;

import com.gmail.pzalejko.cdc.demo.domain.AccountOwner;
import com.gmail.pzalejko.cdc.demo.domain.AccountOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountOwnerRepository accountOwnerRepository;

    @PostMapping("/accounts")
    AccountOwner createAccount(@RequestBody CreateAccountDto dto) {
        var newAccount = AccountOwner.newAccount(dto.name, dto.surname, BigDecimal.valueOf(dto.initSaldo));
        return accountOwnerRepository.save(newAccount);
    }

    @GetMapping("/accounts/{clientId}")
    AccountOwner getAccountOwner(@PathVariable long clientId) {
        return accountOwnerRepository.findById(clientId).orElseThrow();
    }

    record CreateAccountDto(String name, String surname, double initSaldo) {
    }
}
