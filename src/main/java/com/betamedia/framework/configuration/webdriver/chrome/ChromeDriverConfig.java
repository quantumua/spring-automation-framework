package com.betamedia.framework.configuration.webdriver.chrome;

import com.betamedia.framework.components.SUTPropertiesHolder;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.io.File;
import java.io.IOException;
import java.net.URL;

/**
 * Created by mbelyaev on 2/24/17.
 */
//TODO externalize parameter constants
@Configuration
public class ChromeDriverConfig {

    @Bean
    public DesiredCapabilities capabilities() {
        return DesiredCapabilities.chrome();
    }

    //TODO should be a singleton in request scope, investigate how to change
    @Bean(initMethod = "start", destroyMethod = "stop")
    @Scope("prototype")
    public ChromeDriverService chromeDriverService(SUTPropertiesHolder sutPropertiesHolder) throws IOException {
        String driverPath = sutPropertiesHolder.get(SUTPropertiesHolder.CHROME_DRIVER_PATH);
        return new ChromeDriverService.Builder()
                .usingDriverExecutable(new File(driverPath))
                .usingAnyFreePort()
                .build();
    }

    @Bean
    @Scope("prototype")
//    @ConditionalOnMissingBean(name = "remoteDriver")
    public WebDriver driver(DesiredCapabilities capabilities, SUTPropertiesHolder sutPropertiesHolder) throws IOException {
        String remoteDriverUrl= sutPropertiesHolder.get(SUTPropertiesHolder.REMOTE_DRIVER_URL);
        if(remoteDriverUrl != null){
            return new RemoteWebDriver(new URL(remoteDriverUrl), capabilities);
        }
        else {
            String domainUrl = sutPropertiesHolder.get(SUTPropertiesHolder.DOMAIN_URL);
            WebDriver driver = new RemoteWebDriver(chromeDriverService(sutPropertiesHolder).getUrl(), capabilities);
            driver.get(domainUrl);
            return driver;
        }
    }
}
