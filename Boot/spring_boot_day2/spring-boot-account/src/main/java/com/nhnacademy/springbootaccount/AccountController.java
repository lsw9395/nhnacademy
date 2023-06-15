package com.nhnacademy.springbootaccount;

import com.nhnacademy.springbootaccount.configuration.SystemProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AccountController {
    private final AccountService service;

    @GetMapping("/accounts")
    List<Account> getAccounts(){
        return service.getAccounts();
    }
    @GetMapping("/accounts/{id}")
    Account getStudent(@PathVariable Long id){
        return service.getAccount(id);
    }
    @PostMapping("/accounts")
    @ResponseStatus(HttpStatus.CREATED)
    Account createAccount(@RequestBody Account account){
        return service.createAccount(account);
    }

    @DeleteMapping("/accounts/{id}")
    public String deleteAccount(@PathVariable Long id){
        service.deleteAccount(id);
        return "{\"result\":\"OK\"}";
    }

    @Autowired
    private SystemProperties systemProperties;

    @GetMapping("/system/version")
    public String getVersion(){
        return "{\"version\":\""+systemProperties.getVersion()+"\"}";
    }
}
