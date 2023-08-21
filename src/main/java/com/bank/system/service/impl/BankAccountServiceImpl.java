package com.bank.system.service.impl;

import com.bank.system.dto.request.CreateBankAccount;
import com.bank.system.entity.Account;
import com.bank.system.exception.general.NotFoundException;
import com.bank.system.mapper.BankAccountMapper;
import com.bank.system.repository.AccountRepository;
import com.bank.system.service.BankAccountService;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;


@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final BankAccountMapper bankAccountMapper = Mappers.getMapper(BankAccountMapper.class);
    private final AccountRepository accountRepository;

   public BankAccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public Long createBankAccount(CreateBankAccount createBankAccount) {
        Account account = bankAccountMapper.toAccountEntity(createBankAccount);
        return accountRepository.save(account).getId();
    }

    @Override
    public com.bank.system.dto.response.CreateBankAccount getBankAccount(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(NotFoundException::new);
        return bankAccountMapper.toBankAccount(account);
    }

}
