package com.bank.system.dto.request;

import com.bank.system.enums.AccountStatus;
import com.bank.system.enums.AccountType;
import com.bank.system.enums.Currency;
import com.bank.system.validator.AccountNumberValidation.ValidAccountNumber;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import java.math.BigDecimal;
import java.util.Objects;

@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
@Getter
@Setter
public class CreateBankAccount {

    @JsonProperty("balance")
    @NotNull
    @Positive
    @Schema(name = "balance", example = "1000", required = true)
    private BigDecimal balance;

    @JsonProperty("accountNumber")
    @ValidAccountNumber
    @Schema(name = "accountNumber", example = "92377809392910", required = true)
    private String accountNumber;

    @JsonProperty("accountStatus")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Schema(name = "accountStatus", example = "ACTIVE", required = true)
    private AccountStatus accountStatus;

    @JsonProperty("accountType")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Schema(name = "accountType", example = "SAVING", required = true)
    private AccountType accountType;

    @JsonProperty("currency")
    @NotNull
    @Enumerated(EnumType.STRING)
    @Schema(name = "currency", example = "USD", required = true)
    private Currency currency;

    @JsonProperty("customerId")
    @NotNull
    @Schema(name = "customerId", required = true)
    private Long customerId;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateBankAccount bankAccountRequest = (CreateBankAccount) o;
        return Objects.equals(this.balance, bankAccountRequest.balance) &&
                Objects.equals(this.accountNumber, bankAccountRequest.accountNumber) &&
                Objects.equals(this.accountStatus.name(), bankAccountRequest.accountStatus.name()) &&
                Objects.equals(this.accountType.name(), bankAccountRequest.accountType.name()) &&
                Objects.equals(this.currency.name(), bankAccountRequest.currency.name()) &&
                Objects.equals(this.customerId, bankAccountRequest.customerId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(balance, accountNumber, accountStatus, accountType, currency, customerId);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BankAccountRequest {\n");
        sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
        sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
        sb.append("    accountStatus: ").append(toIndentedString(accountStatus)).append("\n");
        sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
        sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
        sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
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

