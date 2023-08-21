package com.bank.system.repository;

import com.bank.system.entity.Account;
import com.bank.system.entity.Transaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Page<Transaction> findByAccountOrderByCreatedAt(Account account, Pageable page);
}
