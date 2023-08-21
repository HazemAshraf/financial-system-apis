package com.bank.system.dto.response;

import com.bank.system.entity.Transaction;
import com.bank.system.enums.Direction;
import com.bank.system.enums.TransactionType;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@Getter
@Setter
public class GetTransactions {

    @JsonProperty("id")
    @NotNull
    @Schema(name = "id", example = "123412341236764512", required = true)
    private Long id;

    @JsonProperty("createdAt")
    @NotNull
    @Schema(name = "createdAt", example = "2023-08-19 15:07:25.659", required = true)
    private Date createdAt;

    @JsonProperty("transactionType")
    @Enumerated(EnumType.STRING)
    @Schema(name = "transactionType", example = "TRANSFER", required = true)
    private TransactionType transactionType;

    @JsonProperty("direction")
    @Enumerated(EnumType.STRING)
    @Schema(name = "direction", example = "INWARD", required = true)
    private Direction direction;

    @JsonProperty("accountId")
    @Schema(name = "accountId", example = "73453111", required = true)
    private Long accountId;

    @JsonProperty("foreignAccount")
    @Schema(name = "foreignAccountId", example = "73453111", required = true)
    private Long foreignAccountId;

    @JsonProperty("accountBalanceBeforeTransaction")
    @Schema(name = "accountBalanceBeforeTransaction", example = "2000", required = true)
    private BigDecimal accountBalanceBeforeTransaction;

    @JsonProperty("amount")
    @Schema(name = "amount", example = "1000", required = true)
    private BigDecimal amount;

    @JsonProperty("accountBalanceAfterTransaction")
    @Schema(name = "accountBalanceAfterTransaction", example = "1000", required = true)
    private BigDecimal accountBalanceAfterTransaction;


    public GetTransactions(Transaction transaction) {
        this.id = transaction.getId();
        this.transactionType = transaction.getTransactionType();
        this.direction = transaction.getDirection();
        this.accountId = transaction.getAccount().getId();
        this.foreignAccountId = transaction.getForeignAccount().getId();
        this.createdAt = transaction.getCreatedAt();
        this.amount = transaction.getAmount();
        this.accountBalanceBeforeTransaction = transaction.getAccountBalanceBeforeTransaction();
        this.accountBalanceAfterTransaction = transaction.getAccountBalanceAfterTransaction();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GetTransactions transactionsResponse = (GetTransactions) o;
        return Objects.equals(this.id, transactionsResponse.id) &&
                Objects.equals(this.createdAt, transactionsResponse.createdAt) &&
                Objects.equals(this.accountId, transactionsResponse.accountId) &&
                Objects.equals(this.foreignAccountId, transactionsResponse.foreignAccountId) &&
                Objects.equals(this.amount, transactionsResponse.amount) &&
                Objects.equals(this.accountBalanceBeforeTransaction, transactionsResponse.accountBalanceBeforeTransaction) &&
                Objects.equals(this.accountBalanceAfterTransaction, transactionsResponse.accountBalanceAfterTransaction) &&
                Objects.equals(this.direction, transactionsResponse.direction) &&
                Objects.equals(this.transactionType, transactionsResponse.transactionType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, createdAt, accountId, foreignAccountId, amount, accountBalanceBeforeTransaction, accountBalanceAfterTransaction, transactionType);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class GetTransactions {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
        sb.append("    accountId: ").append(toIndentedString(accountId)).append("\n");
        sb.append("    foreignAccountId: ").append(toIndentedString(foreignAccountId)).append("\n");
        sb.append("    amountBefore: ").append(toIndentedString(accountBalanceBeforeTransaction)).append("\n");
        sb.append("    amountAfter: ").append(toIndentedString(accountBalanceAfterTransaction)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
        sb.append("}");
        return sb.toString();
    }

    /**
     * Convert the given object to string with each line indented by 4 spaces
     * (except the first line).
     */
    private String toIndentedString(Object o) {
        if (o == null) {
            return "null";
        }
        return o.toString().replace("\n", "\n    ");
    }
}

