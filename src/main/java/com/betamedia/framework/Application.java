package com.betamedia.framework;

import com.betamedia.framework.tests.LoginPageTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

import java.net.MalformedURLException;
import java.net.URL;

@SpringBootApplication
public class Application {

    public static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Application.class, args);
        ITestNGListener tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[]{LoginPageTest.class});
        testng.addListener(tla);
        testng.setParallel(XmlSuite.ParallelMode.METHODS);
        testng.setThreadCount(5);
        testng.run();
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnProperty("remote.driver.url")
    public WebDriver remoteDriver(@Value("${remote.driver.url}") String driverUrl, DesiredCapabilities capabilities) throws MalformedURLException {
        return new RemoteWebDriver(new URL(driverUrl), capabilities);
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean(name = "remoteDriver")
    public WebDriver driver(@Value("${chrome.driver.path}") String chromeDriverPath, DesiredCapabilities capabilities) {
        //TODO implement embedded ChromeDriverService and its lifecycle management as per ChromeDriver javadoc
        switch (capabilities.getBrowserName()) {
            case BrowserType.CHROME:
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                return new ChromeDriver();
            case BrowserType.FIREFOX:
                return new FirefoxDriver();
            default:
                return null;
        }
    }

    @Bean
    public DesiredCapabilities capabilities(@Value("${browser.type}") String browserType) {
        switch (browserType) {
            case BrowserType.CHROME:
                return DesiredCapabilities.chrome();
            case BrowserType.FIREFOX:
                return DesiredCapabilities.firefox();
            default:
                return null;
        }
    }

    public static <T> T getBean(Class<T> clazz) {
        return context.getBean(clazz);
    }
}
