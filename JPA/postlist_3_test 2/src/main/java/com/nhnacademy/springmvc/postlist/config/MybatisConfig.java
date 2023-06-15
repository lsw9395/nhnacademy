package com.nhnacademy.springmvc.postlist.config;

import lombok.RequiredArgsConstructor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
@MapperScan(basePackages = "com.nhnacademy.springmvc.postlist.mapper.**")
@EnableTransactionManagement
public class MybatisConfig {
    private final DataSource dataSource;

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception {
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(resolver.getResources("classpath*:**/maps/*.xml"));
        //camelcase 설정
        //https://mybatis.org/mybatis-3/configuration.html
        sessionFactory.getObject().getConfiguration().setMapUnderscoreToCamelCase(true);
        return sessionFactory;
    }
}
