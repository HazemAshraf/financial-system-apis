package com.bank.system.mapper;


import com.bank.system.dto.request.CreateBankAccount;
import com.bank.system.entity.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface BankAccountMapper {

    @Mapping(target = "customer.id", source = "customerId")
    Account toAccountEntity(CreateBankAccount createBankAccount);

    @Mapping(source = "customer.id", target = "customerId")
    com.bank.system.dto.response.CreateBankAccount toBankAccount(Account account);
}
