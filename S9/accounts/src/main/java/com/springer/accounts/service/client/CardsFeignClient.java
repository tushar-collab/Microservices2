package com.springer.accounts.service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.springer.accounts.dto.CardsDto;

@FeignClient("cards")
public interface CardsFeignClient {

    @GetMapping(value = "api/fetch", consumes = "application/json")
    public ResponseEntity<CardsDto> fetchCardDetails(@RequestHeader("springer-correlation-id") String correlationId,
            @RequestParam String mobileNumber);

}
