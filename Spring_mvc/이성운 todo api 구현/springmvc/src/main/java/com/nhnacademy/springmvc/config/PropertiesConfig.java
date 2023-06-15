package com.nhnacademy.springmvc.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@PropertySource("classpath:url.properties")
public class PropertiesConfig {
    @Getter
    @Value("#{'${excludeUrls}'.split(',')}")
    private Set<String> urls;

}
