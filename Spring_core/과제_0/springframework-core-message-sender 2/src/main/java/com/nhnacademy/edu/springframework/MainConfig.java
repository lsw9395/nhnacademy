package com.nhnacademy.edu.springframework;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableAspectJAutoProxy
@PropertySource("classpath:url.properties")
public class MainConfig {
    @Value("${url}")
    private String url;
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
    @Bean MessageSender smsMessageSender(){
        return new SmsMessageSender();
    }
    @Bean MessageSender emailMessageSender(){
        return new EmailMessagSender();
    }
    @Bean
    public DoorayHookSender doorayHookSender(){
        return new DoorayHookSender(restTemplate(),url);
    }
    @Bean
    public MessageSender doorayMessageSender(){
        return new DoorayMessageSender(doorayHookSender());
    }
    @Bean
    public MessageSendService messageSendService(){
        return new MessageSendService(doorayMessageSender());
    }
}