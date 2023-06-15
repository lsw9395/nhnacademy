package com.nhnacademy.springbootaccount.configuration;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(SystemProperties.class)
public class SystemAutoConfiguration {
}
