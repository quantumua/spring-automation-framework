package com.betamedia.framework.config.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by mbelyaev on 2/24/17.
 */
@Configuration
public class DriverConfig {

    @Bean
    @Scope("prototype")
    @ConditionalOnProperty("remote.driver.url")
    public WebDriver remoteDriver(@Value("${remote.driver.url}") String driverUrl, DesiredCapabilities capabilities) throws MalformedURLException {
        return new RemoteWebDriver(new URL(driverUrl), capabilities);
    }


}
