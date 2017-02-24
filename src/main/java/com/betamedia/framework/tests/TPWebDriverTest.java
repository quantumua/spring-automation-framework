package com.betamedia.framework.tests;

import com.betamedia.framework.Application;
import com.betamedia.framework.web.pages.factory.TPPageFactoryImpl;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public abstract class TPWebDriverTest extends WebDriverTest<TPPageFactoryImpl> {
    @Override
    public TPPageFactoryImpl getPageFactory() {
        return Application.getBean(TPPageFactoryImpl.class);
    }
}
