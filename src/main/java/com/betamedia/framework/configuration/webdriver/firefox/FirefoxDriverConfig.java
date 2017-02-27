package com.betamedia.framework.configuration.webdriver.firefox;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

/**
 * Created by mbelyaev on 2/24/17.
 */
@Configuration
@ConditionalOnProperty(name = "browser.type", havingValue = BrowserType.FIREFOX)
public class FirefoxDriverConfig {

    @Bean
    public DesiredCapabilities capabilities() {
        return DesiredCapabilities.firefox();
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean(name = "remoteDriver")
    public WebDriver driver(DesiredCapabilities capabilities, @Value("${domain.url}") String domainUrl) {
        WebDriver driver = new FirefoxDriver(capabilities);
        driver.get(domainUrl);
        return driver;
    }
}
