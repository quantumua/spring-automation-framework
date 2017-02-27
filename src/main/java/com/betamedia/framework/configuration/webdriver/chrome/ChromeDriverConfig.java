package com.betamedia.framework.configuration.webdriver.chrome;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.io.IOException;

/**
 * Created by mbelyaev on 2/24/17.
 */
//TODO externalize parameter constants
@Configuration
@ConditionalOnProperty(name = "browser.type", havingValue = BrowserType.CHROME)
public class ChromeDriverConfig {

    @Bean
    public DesiredCapabilities capabilities() {
        return DesiredCapabilities.chrome();
    }

    @Bean(initMethod ="start", destroyMethod = "stop")
    @ConditionalOnMissingBean(name = "remoteDriver")
    public ChromeDriverService chromeDriverService(@Value("${chrome.driver.path}") String chromeDriverPath) throws IOException {
        return new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(chromeDriverPath))
                .usingAnyFreePort()
                .build();
    }

    @Bean
    @Scope("prototype")
    @ConditionalOnMissingBean(name = "remoteDriver")
    public WebDriver driver(ChromeDriverService chromeDriverService, DesiredCapabilities capabilities, @Value("${domain.url}") String domainUrl) {
        WebDriver driver = new RemoteWebDriver(chromeDriverService.getUrl(), capabilities);
        driver.get(domainUrl);
        return driver;
    }
}
