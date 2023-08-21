package com.bank.system.mockData;

import com.bank.system.dto.request.CreateTransferTransaction;
import com.bank.system.enums.Direction;
import com.bank.system.enums.TransactionType;

import java.math.BigDecimal;

public class TransferModelMock {

    public static CreateTransferTransaction transferModel() {
        CreateTransferTransaction createTransferTransaction = new CreateTransferTransaction();
        createTransferTransaction.setAmount(new BigDecimal(500));
        createTransferTransaction.setAccount(1L);
        createTransferTransaction.setForeignAccount(2L);
        createTransferTransaction.setDirection(Direction.OUTWARD);
        createTransferTransaction.setTransactionType(TransactionType.DEPOSIT);
        return createTransferTransaction;
    }
}
