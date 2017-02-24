package com.betamedia.framework;

import com.betamedia.framework.business.RunTestHandler;
import com.betamedia.framework.configuration.AppContextHolder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {

    public static void main(String[] args) {
//        args = Arrays.asList("--spring.config.location=driver.properties").toArray(args);

        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        AppContextHolder.setContext(context);
        RunTestHandler runTestHandler = context.getBean(RunTestHandler.class);
        runTestHandler.handle(args);
    }

}
