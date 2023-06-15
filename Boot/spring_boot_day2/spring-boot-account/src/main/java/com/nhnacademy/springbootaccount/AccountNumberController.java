package com.nhnacademy.springbootaccount;

import com.nhnacademy.springbootaccount.configuration.AccountProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountNumberController {
    @Value("${nhn.account.number}")
    private String number;

    @GetMapping("/account-number")
    public String getAccountNumber(){
        return number;
    }
    @Autowired
    private AccountProperties accountProperties;



    @GetMapping("/account-number2")
    public String getAccountNumber2(){
        return accountProperties.getNumber();
    }
}
