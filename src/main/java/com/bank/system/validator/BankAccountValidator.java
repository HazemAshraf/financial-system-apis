package com.bank.system.validator;

import com.bank.system.entity.Account;
import com.bank.system.enums.AccountStatus;
import com.bank.system.enums.Direction;
import com.bank.system.exception.custom.AccountNotActiveStatusException;
import com.bank.system.exception.custom.CurrencyTransferShouldSameException;
import com.bank.system.exception.custom.NotHasEnoughMoneyToTransferException;
import com.bank.system.exception.custom.SameAccountException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BankAccountValidator {

    public void validateAccounts(Account foreignAccount, Account account, Direction direction, BigDecimal amount){
        if(direction.name().equals(Direction.OUTWARD.name())){
            accuontHasEnoughMoney(account.getBalance(),amount);
        }
        else{
            accuontHasEnoughMoney(foreignAccount.getBalance(),amount);
        }
        notSameAccount(foreignAccount, account);
        accountInActiveState(foreignAccount.getAccountStatus());
        accountInActiveState(account.getAccountStatus());
        currencyShouldSame(foreignAccount.getCurrency().name(),account.getCurrency().name());
    }

    private void notSameAccount(Account foreignAccount, Account account) {
        if(foreignAccount.getId().equals(account.getId())){
            throw new SameAccountException();
        }
    }

    private void currencyShouldSame(String senderCurrency, String receiverCurrency) {
        if(!senderCurrency.equals(receiverCurrency)){
            throw new CurrencyTransferShouldSameException();
        }
    }

    private void accountInActiveState(AccountStatus accountStatus) {
        if(!AccountStatus.ACTIVE.equals(accountStatus)){
            throw new AccountNotActiveStatusException();
        }
    }

    private void accuontHasEnoughMoney(BigDecimal balance, BigDecimal amount) {
        if(amount.compareTo(balance) > 0)
            throw new NotHasEnoughMoneyToTransferException();
    }

}
