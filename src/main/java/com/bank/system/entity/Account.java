package com.bank.system.entity;

import com.bank.system.enums.AccountStatus;
import com.bank.system.enums.AccountType;
import com.bank.system.enums.Currency;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "account")
public class Account extends OBaseEntity {
    private static final long serialVersionUID = 1L;


    @Column(name = "balance", nullable = false)
    private BigDecimal balance;

    @Column(name = "account_number", nullable = false, unique = true)
    private String accountNumber;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountStatus accountStatus;


    @Column(name = "account_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "currency", nullable = false)
    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToMany(cascade = {CascadeType.ALL},
            mappedBy = "account",
            fetch = FetchType.LAZY)
    private List<Transaction> transactions;

}
