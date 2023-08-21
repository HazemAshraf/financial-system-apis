package com.bank.system.dto.response;

import com.bank.system.enums.AccountStatus;
import com.bank.system.enums.AccountType;
import com.bank.system.enums.Currency;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@lombok.Builder
@lombok.NoArgsConstructor
@lombok.AllArgsConstructor
public class CreateBankAccount {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("accountNumber")
    @NotNull
    @Schema(name = "accountNumber", example = "92377809392910", required = true)
    private String accountNumber;

    @JsonProperty("balance")
    @NotNull
    @Schema(name = "balance", example = "1000", required = true)
    private BigDecimal balance;

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

    @JsonProperty("createdAt")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date createdAt;


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CreateBankAccount bankAccountResponse = (CreateBankAccount) o;
        return Objects.equals(this.id, bankAccountResponse.id) &&
                Objects.equals(this.accountNumber, bankAccountResponse.accountNumber) &&
                Objects.equals(this.balance, bankAccountResponse.balance) &&
                Objects.equals(this.accountStatus, bankAccountResponse.accountStatus) &&
                Objects.equals(this.accountType, bankAccountResponse.accountType) &&
                Objects.equals(this.currency, bankAccountResponse.currency) &&
                Objects.equals(this.customerId, bankAccountResponse.customerId) &&
                Objects.equals(this.createdAt, bankAccountResponse.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountNumber, balance, accountStatus, accountType, currency, customerId, createdAt);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class BankAccountResponse {\n");
        sb.append("    id: ").append(toIndentedString(id)).append("\n");
        sb.append("    accountNumber: ").append(toIndentedString(accountNumber)).append("\n");
        sb.append("    balance: ").append(toIndentedString(balance)).append("\n");
        sb.append("    accountStatus: ").append(toIndentedString(accountStatus)).append("\n");
        sb.append("    accountType: ").append(toIndentedString(accountType)).append("\n");
        sb.append("    currency: ").append(toIndentedString(currency)).append("\n");
        sb.append("    customerId: ").append(toIndentedString(customerId)).append("\n");
        sb.append("    createdAt: ").append(toIndentedString(createdAt)).append("\n");
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

