package com.springer.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Schema(name = "Customer", description = "Schema to hold customer, accoutns, cards and loans details")
@Data
public class CustomerDetailsDto {
    @Schema(description = "Name of the customer")
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 5, max = 20, message = "Name should be between 5 and 20 characters")
    private String name;

    @Schema(description = "Email of the customer", example = "7Eo0f@example.com")
    @NotEmpty(message = "Email should not be empty")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(description = "Mobile number of the customer", example = "1234567890")
    @NotEmpty(message = "Mobile number should not be empty")
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    private AccountsDto accountsDto;

    private CardsDto cardsDto;

    private LoansDto loansDto;
}
