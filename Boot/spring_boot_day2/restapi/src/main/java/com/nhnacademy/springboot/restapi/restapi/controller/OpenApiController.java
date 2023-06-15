package com.nhnacademy.springboot.restapi.restapi.controller;

import com.nhnacademy.springboot.restapi.restapi.domain.Account;
import com.nhnacademy.springboot.restapi.restapi.service.OpenApiService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OpenApiController {
    private final OpenApiService service;

    @GetMapping("/accounts")
    public List<Account> getAccounts(){
        return service.getAccounts();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable Long id){
        return service.getAccount(id);
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account){
        return service.createAccount(account);
    }

    @DeleteMapping("/accounts/{id}")
    public String deleteAccount(@PathVariable Long id){
        return service.deleteAccount(id);
    }
}
