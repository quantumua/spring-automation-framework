package com.betamedia.framework.components.impl;

import com.betamedia.framework.components.AbstractPageService;
import com.betamedia.framework.web.pages.tp.login.DisclaimerNotification;
import com.betamedia.framework.web.pages.tp.login.LoginErrorNotification;
import com.betamedia.framework.web.pages.tp.login.LoginPage;
import com.betamedia.framework.web.pages.tp.login.impl.DisclaimerNotificationImpl;
import com.betamedia.framework.web.pages.tp.login.impl.LoginErrorNotificationImpl;
import com.betamedia.framework.web.pages.tp.login.impl.LoginPageImpl;
import com.betamedia.framework.web.pages.tp.navigation.TopNavigationPage;
import com.betamedia.framework.web.pages.tp.navigation.TopNavigationPageImpl;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by mbelyaev on 2/23/17.
 */
@Service
public class PageServiceImpl extends AbstractPageService {

    private final WebDriver driver;
    private final WebElementRepository repository;

    @Autowired
    public PageServiceImpl(WebDriver driver, WebElementRepository repository) {
        this.driver = driver;
        this.repository = repository;
    }

    public void closeBrowser(){
        driver.close();
    }

    @Override
    protected WebDriver getDriver() {
        return driver;
    }

    @Override
    protected WebElementRepository getRepository() {
        return repository;
    }

    public LoginPage loginPage() {
        return getPage(LoginPageImpl.class);
    }

    public TopNavigationPage topNavigationPage() {
        return getPage(TopNavigationPageImpl.class);
    }

    public DisclaimerNotification disclaimerNotification() {
        return getPage(DisclaimerNotificationImpl.class);
    }


    public LoginErrorNotification loginErrorNotification() {
        return getPage(LoginErrorNotificationImpl.class);
    }
}
