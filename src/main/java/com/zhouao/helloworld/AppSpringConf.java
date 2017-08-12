package com.zhouao.helloworld;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppSpringConf {

    @Bean(name = "sayHello")
    public SayHello sayHello() {
        return new SayHello();
    }
}