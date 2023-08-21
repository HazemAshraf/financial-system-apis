package com.bank.system.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class ErrorApiResponse {

    @JsonProperty("code")
    @Schema(name = "code", example = "215", required = false)
    private Integer code;

    @JsonProperty("message")
    @Schema(name = "message", example = "Failed to convert value of type [String]  to required type [Long];", required = false)
    private String message;


    public ErrorApiResponse(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ErrorApiResponse errorApiResponse = (ErrorApiResponse) o;
        return Objects.equals(this.code, errorApiResponse.code) &&
                Objects.equals(this.message, errorApiResponse.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, message);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("class ErrorApiResponse {\n");
        sb.append("    code: ").append(toIndentedString(code)).append("\n");
        sb.append("    message: ").append(toIndentedString(message)).append("\n");
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

