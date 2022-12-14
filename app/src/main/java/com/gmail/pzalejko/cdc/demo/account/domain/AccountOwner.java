package com.gmail.pzalejko.cdc.demo.account.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class AccountOwner {

    static AccountOwner newAccountOwner(String name, String surname, BigDecimal initBalance) {
        var owner = new AccountOwner();
        owner.setName(name);
        owner.setSurname(surname);

        var account = Account.openNewAccount(owner, initBalance);
        owner.getAccounts().add(account);

        return owner;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String surname;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "accountOwner")
    List<Account> accounts = new ArrayList<>();

    public String getFullName() {
        return String.format("%s %s", name, surname);

    }
}
