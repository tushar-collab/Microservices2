package com.springer.accounts.service;

import com.springer.accounts.dto.CustomerDetailsDto;

public interface ICustomerService {
    
    CustomerDetailsDto fetchCustomerDetails(String mobileNumber, String correlationId);

}
