package com.gmail.pzalejko.cdc.demo.account.application;

import com.gmail.pzalejko.cdc.demo.account.domain.AccountOwner;
import com.gmail.pzalejko.cdc.demo.account.domain.AccountOwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class AccountController {

    private final AccountOwnerService service;

    @PostMapping("/accounts")
    AccountOwner createAccount(@RequestBody AccountOwnerService.CreateAccountDto dto) {
        return service.createAccount(dto);
    }
}
