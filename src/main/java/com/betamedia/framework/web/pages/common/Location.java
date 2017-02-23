package com.betamedia.framework.web.pages.common;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by mbelyaev on 2/17/17.
 */
public abstract class Location extends AbstractPageService{
    abstract public By getLocator();

    public void waitFor() {
        WebDriverWait wait = new WebDriverWait(driver, 60);
        wait.until(ExpectedConditions.elementToBeClickable(getLocator()));
    }

    public boolean isAt() {
        return driver.findElements(getLocator()).size() > 0;
    }
}