package com.springer.accounts.service;

import com.springer.accounts.dto.CustomerDto;

public interface IAccountsService {

    /**
     * @param customerDto
     **/
    void createAccount(CustomerDto customerDto);

    CustomerDto fetchAccount(String mobileNumber);

    boolean updateAccount(CustomerDto customerDto);

    boolean deleteAccount(String mobileNumber);
}
