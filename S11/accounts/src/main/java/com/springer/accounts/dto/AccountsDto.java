package com.springer.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Schema(description = "Accounts details", name = "Accounts")
@Data
public class AccountsDto {

    @Schema(description = "Account number", example = "1234567890")
    @NotEmpty
    @Pattern(regexp = "^\\d{10}$", message = "Account number should be 10 digits")
    private Long accountNumber;

    @Schema(description = "Account type", example = "Savings")
    @NotEmpty(message = "Account type should not be empty")
    private String accountType;

    @Schema(description = "Branch address", example = "123 Main Street, New York")
    @NotEmpty(message = "Branch address should not be empty")
    private String branchAddress;

}
