package com.springer.accounts.mapper;

import com.springer.accounts.dto.AccountsDto;
import com.springer.accounts.entity.Accounts;

public class AccountsMapper {

   public static AccountsDto mapToAccountsDto(Accounts accounts, AccountsDto accountsDto) {
       accountsDto.setAccountNumber(accounts.getAccountNumber());
       accountsDto.setAccountType(accounts.getAccountType());
       accountsDto.setBranchAddress(accounts.getBranchAddress());
       return accountsDto;
   }

   public static Accounts mapToAccounts(AccountsDto accountsDto, Accounts accounts) {
       accounts.setAccountNumber(accountsDto.getAccountNumber());
       accounts.setAccountType(accountsDto.getAccountType());
       accounts.setBranchAddress(accountsDto.getBranchAddress());
       return accounts;
   }

}
