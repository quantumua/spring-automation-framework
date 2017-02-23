package com.betamedia.framework.web.pages.tp.login.impl;

import com.betamedia.framework.web.pages.common.AbstractPage;
import com.betamedia.framework.web.pages.common.annotation.StoredId;
import com.betamedia.framework.web.pages.tp.login.DisclaimerNotification;
import org.openqa.selenium.By;

import java.util.List;

/**
 * Created by mbelyaev on 2/16/17.
 */
public class DisclaimerNotificationImpl extends AbstractPage implements DisclaimerNotification {
    @StoredId("disclaimerCheckbox")
    private By disclaimerCheckbox;
    @StoredId("disclaimerSubmitBtn")
    private By disclaimerSubmitBtn;

    @Override
    public void accept() {
        waitFor();
        driver.findElement(disclaimerCheckbox).click();
        driver.findElement(disclaimerSubmitBtn).click();
    }

    @Override
    public By getLocator() {
        return disclaimerCheckbox;
    }
}