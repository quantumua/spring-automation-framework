package com.betamedia.framework.pages.factory;

import com.betamedia.framework.pages.tp.login.DisclaimerNotification;
import com.betamedia.framework.pages.tp.login.LoginErrorNotification;
import com.betamedia.framework.pages.tp.login.LoginPage;
import com.betamedia.framework.pages.tp.navigation.TopNavigationPage;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TPPageFactory {
    LoginPage loginPage();

    TopNavigationPage topNavigationPage();

    DisclaimerNotification disclaimerNotification();

    LoginErrorNotification loginErrorNotification();
}
