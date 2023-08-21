package com.bank.system.entity;

import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customer")
public class Customer extends OBaseEntity {
    private static final long serialVersionUID = 1L;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(
            fetch = FetchType.LAZY,
            mappedBy = "customer",
            cascade = {CascadeType.ALL})
    private List<Account> accounts;
}
