package com.gmail.pzalejko.cdc.demo.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Entity
@NoArgsConstructor
public class AccountHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Account from;
    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    private Account to;

    private BigDecimal value;
    private Operation operation;
    private Timestamp timestamp;

    public AccountHistory(Account account, Account from, Account to, BigDecimal value, Operation operation, Timestamp timestamp) {
        this.account = account;
        this.from = from;
        this.to = to;
        this.value = value;
        this.operation = operation;
        this.timestamp = timestamp;
    }

    enum Operation {
        ADDED,
        REMOVED
    }

}
