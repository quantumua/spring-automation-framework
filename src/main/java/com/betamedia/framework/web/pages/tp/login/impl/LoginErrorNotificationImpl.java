package com.betamedia.framework.web.pages.tp.login.impl;

import com.betamedia.framework.web.pages.common.annotation.StoredId;
import com.betamedia.framework.web.pages.tp.login.LoginErrorNotification;
import org.openqa.selenium.By;

/**
 * Created by mbelyaev on 2/17/17.
 */
public class LoginErrorNotificationImpl extends LoginErrorNotification {

    @StoredId("errorNotification")
    private By errorNotification;
    @StoredId("errorCloseBtn")
    private By errorCloseBtn;

    @Override
    public By getLocator() {
        return errorNotification;
    }

    @Override
    public void dismiss() {
        waitFor();
        driver.findElement(errorCloseBtn).click();
    }
}
