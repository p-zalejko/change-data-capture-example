package com.gmail.pzalejko.cdc.demo.domain;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor
@Data
public class AccountDetails {

    public static AccountDetails newAccount(String name, String surname){
        var details  = new AccountDetails();
        details.setName(name);
        details.setSurname(surname);

        Account account = new Account();
        account.setAccountDetails(details);
        details.getAccounts().add(account);

        return details;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String name;
    String surname;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "accountDetails")
    List<Account> accounts = new ArrayList<>();
}
