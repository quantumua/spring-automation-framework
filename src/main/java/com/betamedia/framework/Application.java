package com.betamedia.framework;

import com.betamedia.framework.tests.LoginPageTest;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

@SpringBootApplication
public class Application {

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Application.class, args);

        ITestNGListener tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[] { LoginPageTest.class });
        testng.addListener(tla);
        testng.run();

    }

    @Bean
    @Scope("prototype")
    public WebDriver driver() {
        System.setProperty("webdriver.chrome.driver", "/opt/chromium-browser/chromedriver");
        return new ChromeDriver();
    }

    public static <T> T getBean(Class<T> clazz){
        return context.getBean(clazz);
    }
}
