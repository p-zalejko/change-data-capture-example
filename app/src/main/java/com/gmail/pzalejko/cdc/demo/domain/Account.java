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

    public void withdrawTo(@NonNull Account to, @NonNull BigDecimal value, @NonNull Instant timestamp) {
        if (saldo.compareTo(value) < 0) {
            throw new IllegalArgumentException("No enough money...");
        }
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Cannot move zero or less...");
        }

        saldo = saldo.subtract(value);
        history.add(new AccountHistory(this, this, to, value, AccountHistory.Operation.REMOVED, Timestamp.from(timestamp)));
    }

    public void depositFrom(@NonNull Account from, @NonNull BigDecimal value, @NonNull Instant timestamp) {
        if (value.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Cannot move zero or less...");
        }

        saldo = saldo.add(value);
        history.add(new AccountHistory(this, from, this, value, AccountHistory.Operation.ADDED, Timestamp.from(timestamp)));
    }
}
