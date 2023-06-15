package com.nhnacademy.springboot.restapi.restapi.service;

import com.nhnacademy.springboot.restapi.restapi.domain.Account;

import java.util.List;

public interface RestApiService {
    List<Account> getAccounts();

    Account getAccount(Long id);

    Account createAccount(Account account);

    String deleteAccount(Long id);
}
