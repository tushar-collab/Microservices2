package com.springer.accounts.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(name = "Cards", description = "Cards details")
public class CardsDto {

    @NotEmpty
    @Schema(description = "Card id", example = "1234567890")
    private String cardId;

    @NotEmpty
    @Schema(description = "Card number", example = "1234567890")
    private String cardType;

    @Schema(description = "Total limit", example = "1000")
    private Integer totalLimit;

    @Schema(description = "Amount used", example = "500")
    private Integer amountUsed;

    @Schema(description = "Available amount", example = "500")
    private Integer availableAmount;

    @Schema(description = "Mobile number", example = "1234567890")
    @NotEmpty
    @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits")
    private String mobileNumber;

    @NotEmpty
    @Schema(description = "Card number", example = "1234567890")
    private String cardNumber;

}
