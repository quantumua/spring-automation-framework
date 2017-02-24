package com.betamedia.framework.configuration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Configuration
public class AppConfiguration {

    @Bean
    @Scope("prototype")
    @ConditionalOnProperty("remote.driver.url")
    public WebDriver remoteDriver(@Value("${remote.driver.url}") String driverUrl, @Value("${domain.url}") String domainUrl,
                                  DesiredCapabilities capabilities) throws MalformedURLException {
        RemoteWebDriver remoteWebDriver = new RemoteWebDriver(new URL(driverUrl), capabilities);
        remoteWebDriver.get(domainUrl);
        return remoteWebDriver;
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean(name = "remoteDriver")
    public WebDriver driver(@Value("${chrome.driver.path}") String chromeDriverPath, @Value("${domain.url}") String domainUrl,
                            DesiredCapabilities capabilities) {
        //TODO implement embedded ChromeDriverService and its lifecycle management as per ChromeDriver javadoc
        switch (capabilities.getBrowserName()) {
            case BrowserType.CHROME:
                System.setProperty("webdriver.chrome.driver", chromeDriverPath);
                ChromeDriver chromeDriver = new ChromeDriver();
                chromeDriver.get(domainUrl);
                return chromeDriver;
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
}
