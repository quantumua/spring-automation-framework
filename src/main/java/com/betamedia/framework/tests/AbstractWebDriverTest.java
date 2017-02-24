package com.betamedia.framework.tests;


import com.betamedia.framework.web.pages.factory.AbstractPageFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;


/**
 * Created by mbelyaev on 2/21/17.
 */
public abstract class AbstractWebDriverTest<T extends AbstractPageFactory> {

    protected T pages;

    public abstract T getPageFactory();

    @BeforeMethod
    public void setUp() throws Exception {
        pages = getPageFactory();
    }

    @AfterMethod
    public void tearDown() throws Exception {
        pages.closeBrowser();
    }
    
}
