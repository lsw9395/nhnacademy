package com.nhnacademy.springboot.restapi.restapi.service;

import com.nhnacademy.springboot.restapi.restapi.domain.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class OpenApiService implements RestApiService{

    private final RestTemplate restTemplate;
    private static final String URL = "http://localhost:9090/accounts";
    @Override
    public List<Account> getAccounts() {
        HttpEntity<String> requestEntity = getHttpEntity();
        ResponseEntity<List<Account>> exchange = restTemplate.exchange(URL, HttpMethod.GET,requestEntity, new ParameterizedTypeReference<List<Account>>(){});
        return exchange.getBody();
    }

    @Override
    public Account getAccount(Long id) {
        HttpEntity<String> requestEntity = getHttpEntity();
        ResponseEntity<Account> exchange = restTemplate.exchange(URL+"/"+id, HttpMethod.GET,requestEntity, Account.class);
        return exchange.getBody();
    }

    @Override
    public Account createAccount(Account account) {
        HttpEntity<Map<String,Object>> requestEntity = getHttpEntityAddParams(account);
        return restTemplate.exchange(URL,HttpMethod.POST,requestEntity, Account.class).getBody();
    }

    @Override
    public String deleteAccount(Long id) {
        HttpEntity<String> requestEntity = getHttpEntity();
        return restTemplate.exchange(URL+"/"+id,HttpMethod.DELETE,requestEntity,String.class).getBody();
    }

    public HttpEntity getHttpEntity(){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> requestEntity = new HttpEntity<>(headers);
        return requestEntity;
    }
    public HttpEntity getHttpEntityAddParams(Account account){
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(List.of(MediaType.APPLICATION_JSON));
        Map<String, Object> params = new HashMap<>();
        params.put("id",account.getId());
        params.put("balance",account.getBalance());
        params.put("number",account.getNumber());
        HttpEntity<Map<String,Object>> requestEntity = new HttpEntity<>(params,headers);
        return requestEntity;
    }
}
