package com.nhnacademy.springbootaccount;

import java.util.List;

public interface AccountService {
    List<Account> getAccounts();

    Account getAccount(Long id);

    void save(Account account);
    Account createAccount(Account account);

    void deleteAccount(Long id);
}
