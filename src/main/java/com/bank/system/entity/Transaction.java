package com.bank.system.entity;

import com.bank.system.enums.Direction;
import com.bank.system.enums.TransactionType;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "transaction")
public class Transaction extends OBaseEntity {
    private static final long serialVersionUID = 1L;


    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Column(name = "direction", nullable = false)
    @Enumerated(EnumType.STRING)
    private Direction direction;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "accountBalanceBeforeTransaction", nullable = false)
    private BigDecimal accountBalanceBeforeTransaction;

    @Column(name = "accountBalanceAfterTransaction", nullable = false)
    private BigDecimal accountBalanceAfterTransaction;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "id", nullable = false)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "foreign_account_id", referencedColumnName = "id", nullable = false)
    private Account foreignAccount;

}
