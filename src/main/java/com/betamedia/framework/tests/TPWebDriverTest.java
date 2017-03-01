package com.betamedia.framework.tests;

import com.betamedia.framework.configuration.AppContextHolder;
import com.betamedia.framework.pages.factory.TPPageFactoryImpl;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public abstract class TPWebDriverTest extends AbstractWebDriverTest<TPPageFactoryImpl> {


    @Override
    public TPPageFactoryImpl getPageFactory() {
        return AppContextHolder.getBean(TPPageFactoryImpl.class);
    }

}
