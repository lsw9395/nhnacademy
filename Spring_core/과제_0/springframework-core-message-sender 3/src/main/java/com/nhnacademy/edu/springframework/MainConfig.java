package com.nhnacademy.edu.springframework;

import org.springframework.context.annotation.*;


@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.nhnacademy.edu.springframework")
@PropertySource("classpath:url.properties")
public class MainConfig {

}