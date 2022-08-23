package com.gmail.pzalejko.cdc.demo;

import com.gmail.pzalejko.cdc.demo.domain.AccountDetails;
import com.gmail.pzalejko.cdc.demo.domain.AccountDetailsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountDetailsRepository accountDetailsRepository;

    @PostMapping("/accounts")
    AccountDetails createAccountDetails(@RequestBody CreateAccountDto dto) {
        var newAccount = AccountDetails.newAccount(dto.name, dto.surname);
        return accountDetailsRepository.save(newAccount);
    }

    @GetMapping("/accounts")
    List<AccountDetails> getAccountDetails() {
        return accountDetailsRepository.findAll();
    }

    @GetMapping("/accounts/{clientId}")
    AccountDetails getAccountDetails(@PathVariable long clientId) {
        return accountDetailsRepository.findById(clientId).orElseThrow();
    }

    record CreateAccountDto(String name, String surname) {
    }
}
