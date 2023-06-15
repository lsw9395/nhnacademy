package com.nhnacademy.springmvc.postlist.config;


import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@PropertySource("classpath:url.properties")
public class PropertiesConfig {
    @Getter
    @Value("#{'${excludeUrls}'.split(',')}")
    private Set<String> urls;

}
