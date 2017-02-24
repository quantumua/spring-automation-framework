package com.betamedia.framework.web.pages.factory;

import com.betamedia.framework.web.pages.common.type.AppType;
import com.betamedia.framework.web.pages.tp.login.DisclaimerNotification;
import com.betamedia.framework.web.pages.tp.login.LoginErrorNotification;
import com.betamedia.framework.web.pages.tp.login.LoginPage;
import com.betamedia.framework.web.pages.tp.login.impl.DisclaimerNotificationImpl;
import com.betamedia.framework.web.pages.tp.login.impl.LoginErrorNotificationImpl;
import com.betamedia.framework.web.pages.tp.login.impl.LoginPageImpl;
import com.betamedia.framework.web.pages.tp.navigation.TopNavigationPage;
import com.betamedia.framework.web.pages.tp.navigation.TopNavigationPageImpl;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Component
@Scope("prototype")
public class TPPageFactoryImpl extends AbstractPageFactory implements TPPageFactory {
    @Override
    public LoginPage loginPage() {
        return creator.getPage(LoginPageImpl.class);
    }

    @Override
    public TopNavigationPage topNavigationPage() {
        return creator.getPage(TopNavigationPageImpl.class);
    }

    @Override
    public DisclaimerNotification disclaimerNotification() {
        return creator.getPage(DisclaimerNotificationImpl.class);
    }

    @Override
    public LoginErrorNotification loginErrorNotification() {
        return creator.getPage(LoginErrorNotificationImpl.class);
    }

    @Override
    public AppType getType() {
        return AppType.TP;
    }
}
