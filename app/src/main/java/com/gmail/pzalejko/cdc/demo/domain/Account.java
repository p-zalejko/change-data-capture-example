package com.gmail.pzalejko.cdc.demo.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.NonNull;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    BigDecimal saldo = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "accountDetails_id", nullable = false)
     @Setter(AccessLevel.MODULE)
    AccountDetails accountDetails;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "account")
    List<AccountHistory> history = new ArrayList<>();

    public void withdrawTo(@NonNull Account to, @NonNull BigDecimal value) {
        if (saldo.compareTo(value) < 0) {
            throw new IllegalArgumentException("No enough money...");
        }

        saldo = saldo.subtract(value);

        history.add(new AccountHistory(this, this, to, value, AccountHistory.Operation.REMOVED, Timestamp.from(Instant.now())));
    }

    public void depositFrom(Account from, BigDecimal value) {
        saldo = saldo.add(value);
        history.add(new AccountHistory(this, from, this, value, AccountHistory.Operation.ADDED, Timestamp.from(Instant.now())));
    }
}
