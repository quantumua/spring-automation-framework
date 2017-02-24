package com.betamedia.framework.web.pages.factory;

import com.betamedia.framework.web.pages.tp.login.DisclaimerNotification;
import com.betamedia.framework.web.pages.tp.login.LoginErrorNotification;
import com.betamedia.framework.web.pages.tp.login.LoginPage;
import com.betamedia.framework.web.pages.tp.navigation.TopNavigationPage;

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
