package com.springer.accounts.dto;

import java.util.List;
import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("accounts")
public record AccountsContactInfoDto(String message, Map<String,String> contactDetails, List<String> onCallSupport) {
    


}
