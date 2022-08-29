package com.gmail.pzalejko.cdc.demo.cdc;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(indexes = {
        @Index(name = "accountId", columnList = "accountId", unique = true)
})
@NoArgsConstructor
class AccountStateCdc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @NotNull
    private String name;

    // here, are just longs, not any JOINs...
    @NotNull
    private Long accountId;
    @NotNull
    private Long accountOwnerId;

    @Setter
    private BigDecimal balance = BigDecimal.ZERO;

    AccountStateCdc(String name, Long accountId, Long accountOwnerId) {
        this.name = name;
        this.accountId = accountId;
        this.accountOwnerId = accountOwnerId;
    }
}
