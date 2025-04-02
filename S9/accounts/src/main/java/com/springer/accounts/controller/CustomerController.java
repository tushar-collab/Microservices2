package com.springer.accounts.controller;

import org.apache.hc.core5.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springer.accounts.dto.CustomerDetailsDto;
import com.springer.accounts.service.ICustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;

@RestController
@RequestMapping(path = "/api", produces = { MediaType.APPLICATION_JSON_VALUE })
@Validated
@Tag(name = "REST APIs for customer in bank", description = "REST APIs for customer in bank")
public class CustomerController {

    private final ICustomerService iCustomerService;

    private static final Logger LOG = LoggerFactory.getLogger(CustomerController.class);

    public CustomerController(ICustomerService customerService) {
        this.iCustomerService = customerService;
    }

    @Operation(summary = "Fetch customer details", description = "Fetch customer details")
    @ApiResponse(responseCode = "200", description = "Customer details fetched successfully")
    @GetMapping("/fetchCustomerDetails")
    public ResponseEntity<CustomerDetailsDto> fetchCustomerDetails(
            @RequestHeader("springer-correlation-id") String correlationId,
            @Pattern(regexp = "^\\d{10}$", message = "Mobile number should be 10 digits") @RequestParam String mobileNumber) {
        LOG.info("Correlation ID: {}", correlationId);
        CustomerDetailsDto customerDetailsDto = iCustomerService.fetchCustomerDetails(mobileNumber,correlationId);
        return ResponseEntity.status(HttpStatus.SC_OK).body(customerDetailsDto);
    }

}
