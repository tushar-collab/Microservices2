package com.springer.accounts.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springer.accounts.dto.AccountsDto;
import com.springer.accounts.dto.CardsDto;
import com.springer.accounts.dto.CustomerDetailsDto;
import com.springer.accounts.dto.LoansDto;
import com.springer.accounts.entity.Accounts;
import com.springer.accounts.entity.Customer;
import com.springer.accounts.exception.ResourceNotFoundException;
import com.springer.accounts.mapper.AccountsMapper;
import com.springer.accounts.mapper.CustomerMapper;
import com.springer.accounts.repository.AccountsRepository;
import com.springer.accounts.repository.CustomerRepository;
import com.springer.accounts.service.ICustomerService;
import com.springer.accounts.service.client.CardsFeignClient;
import com.springer.accounts.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {

        private AccountsRepository accountsRepository;
        private CustomerRepository customerRepository;
        private CardsFeignClient cardsFeignClient;
        private LoansFeignClient loansFeignClient;

        @Override
        public CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId) {
                Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber));

                Accounts account = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                                () -> new ResourceNotFoundException("Account", "customerId",
                                                customer.getCustomerId().toString()));

                CustomerDetailsDto customerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer,
                                new CustomerDetailsDto());
                customerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(account, new AccountsDto()));

                ResponseEntity<LoansDto> loansDResponseEntity = loansFeignClient.fetchLoanDetails(correlationId,
                                mobileNumber);
                if (null != loansDResponseEntity) {
                        customerDetailsDto.setLoansDto(loansDResponseEntity.getBody());
                }

                ResponseEntity<CardsDto> cardsResponseEntity = cardsFeignClient.fetchCardDetails(correlationId,
                                mobileNumber);
                if (null != cardsResponseEntity) {
                        customerDetailsDto.setCardsDto(cardsResponseEntity.getBody());
                }

                return customerDetailsDto;
        }

}
