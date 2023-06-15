package com.nhnacademy.exam;

import com.nhnacademy.exam.intercepter.AuthInterceptor;
import com.nhnacademy.exam.service.DBSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final DBSettingService dbSettingService;
    @Autowired
    private AuthInterceptor authInterceptor;
    @Bean
    public void dataInitialize() throws IOException {
        String fileName= "department.csv";
        dbSettingService.dbSetting(fileName);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }
}
