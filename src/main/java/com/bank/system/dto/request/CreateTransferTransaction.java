package com.bank.system.dto.request;

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
import java.util.Objects;

@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@Getter
@Setter
public class CreateTransferTransaction {

    @JsonProperty("account")
    @NotNull
    @Schema(name = "account", example = "73453434", required = true)
    private Long account;

    @JsonProperty("foreignAccount")
    @NotNull
    @Schema(name = "foreignAccount", example = "73453111", required = true)
    private Long foreignAccount;

    @JsonProperty("amount")
    @NotNull
    @Schema(name = "amount", example = "500", required = true)
    private BigDecimal amount;


    @JsonProperty("transactionType")
    @Enumerated(EnumType.STRING)
    @NotNull
    @Schema(name = "transactionType", example = "TRANSFER", required = true)
    private TransactionType transactionType;


    @JsonProperty("direction")
    @Enumerated(EnumType.STRING)
    @NotNull
    @Schema(name = "direction", example = "OUTWARD", required = true)
    private Direction direction;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateTransferTransaction transferModel = (CreateTransferTransaction) o;
        return Objects.equals(this.foreignAccount, transferModel.foreignAccount) &&
                Objects.equals(this.account, transferModel.account) &&
                Objects.equals(this.amount, transferModel.amount) &&
                Objects.equals(this.direction.name(), transferModel.direction.name()) &&
                Objects.equals(this.transactionType.name(), transferModel.transactionType.name());
    }

    @Override
    public int hashCode() {
        return Objects.hash(foreignAccount, account, amount, transactionType, direction);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class CreateTransfer {\n");
        sb.append("    foreignAccount: ").append(toIndentedString(foreignAccount)).append("\n");
        sb.append("    account: ").append(toIndentedString(account)).append("\n");
        sb.append("    amount: ").append(toIndentedString(amount)).append("\n");
        sb.append("    transactionType: ").append(toIndentedString(transactionType)).append("\n");
        sb.append("    direction: ").append(toIndentedString(direction)).append("\n");
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

