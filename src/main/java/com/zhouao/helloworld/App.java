package com.zhouao.helloworld;


import org.apache.log4j.xml.DOMConfigurator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    private static Logger log = LoggerFactory.getLogger(App.class);

    public static void main( String[] args )
    {
        DOMConfigurator.configure("log4j.xml");
        log.debug("Hello World");

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppSpringConf.class);

        SayHello s = (SayHello) ctx.getBean("sayHello");
        s.say();;
    }
}
