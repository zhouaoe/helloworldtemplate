package com.zhouao.springtest.ioc;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainIoc {
    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppSpringConf.class);

        SayHello s = (SayHello) ctx.getBean("sayHello");
        s.say();
    }
}
