package com.nhnacademy.springbootaccount.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "nhn.account")
@Getter
@Setter
public class AccountProperties {
    private String number;
}
