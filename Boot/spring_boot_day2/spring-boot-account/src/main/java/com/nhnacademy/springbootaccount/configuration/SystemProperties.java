package com.nhnacademy.springbootaccount.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "com.nhn.account.system")
@Getter
@Setter
public class SystemProperties {
    private String version;
}
