package com.nhnacademy.exam;

import com.nhnacademy.exam.intercepter.AuthInterceptor;
import com.nhnacademy.exam.service.DBSettingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.IOException;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final DBSettingService dbSettingService;
    @Autowired
    private AuthInterceptor authInterceptor;
    @Value("${init.file.name}")
    private List<String> datafile;
    @Bean
    public void dataInitialize() throws IOException {
        for(String fileName:datafile){
            dbSettingService.dbSetting(fileName);
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
    }
}
