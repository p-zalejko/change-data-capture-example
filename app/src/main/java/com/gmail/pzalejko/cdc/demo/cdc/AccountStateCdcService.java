package com.gmail.pzalejko.cdc.demo.cdc;

import com.gmail.pzalejko.cdc.demo.account.domain.Account;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AccountStateCdcService {

    private final AccountStateCdcRepository repository;

    @Transactional("transactionManager")
    public void updateStateCdc(@NonNull Account account) {
        var byAccountId = getAccountStateCdc(account);
        byAccountId.setBalance(account.getBalance());
        repository.save(byAccountId);
    }

    private AccountStateCdc getAccountStateCdc(Account account) {
        var accountOwner = account.getAccountOwner();
        return repository.findByAccountId(account.getId())
                .orElse(new AccountStateCdc(
                        String.format("%s %s", accountOwner.getName(), accountOwner.getSurname()),
                        account.getId(),
                        accountOwner.getId())
                );
    }
}
