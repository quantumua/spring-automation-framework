package com.betamedia.framework;

import com.betamedia.framework.tests.LoginPageTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

@SpringBootApplication
public class Application {

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Application.class, args);

        TestListenerAdapter tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] { LoginPageTest.class });
        testng.addListener(tla);
        testng.run();

    }

    @Bean
    public WebDriver driver() {
        return new ChromeDriver();
    }

    public static <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }
}
