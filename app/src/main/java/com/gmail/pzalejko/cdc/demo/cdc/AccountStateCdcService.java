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
        return repository.findByAccountId(account.getId())
                .orElse(new AccountStateCdc(
                        account.getAccountOwner().getFullName(),
                        account.getId(),
                        account.getAccountOwner().getId())
                );
    }
}
