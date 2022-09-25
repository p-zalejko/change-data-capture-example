package com.gmail.pzalejko.cdc.demo.account.domain;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@RequiredArgsConstructor
public class AccountOwnerService {
    private final AccountOwnerRepository accountOwnerRepository;

    public AccountOwner createAccountOwner(@NonNull CreateAccountDto dto) {
        var newAccount = AccountOwner.newAccountOwner(dto.name, dto.surname, BigDecimal.valueOf(dto.initBalance));
        return accountOwnerRepository.save(newAccount);
    }

    public record CreateAccountDto(@NonNull String name, @NonNull String surname, double initBalance) {
    }

}
