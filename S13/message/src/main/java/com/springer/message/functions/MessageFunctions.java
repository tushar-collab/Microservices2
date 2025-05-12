package com.springer.message.functions;

import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.springer.message.dto.AccountsMessageDto;

@Configuration
public class MessageFunctions {

    public static final Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

    @Bean
    public Function<AccountsMessageDto, AccountsMessageDto> email() {
        return accountsMsgDto -> {
            logger.info("Sending email with the details:" + accountsMsgDto.toString());
            return accountsMsgDto;
        };
    }

    @Bean
    public Function<AccountsMessageDto, Long> sms() {
        return accountsMsgDto -> {
            logger.info("Sending sms with the details:" + accountsMsgDto.toString());
            return accountsMsgDto.accountNumber();
        };
    }
}
