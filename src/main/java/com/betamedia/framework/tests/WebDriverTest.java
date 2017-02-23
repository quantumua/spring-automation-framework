package com.betamedia.framework.tests;


import com.betamedia.framework.components.impl.PageServiceImpl;
import com.betamedia.framework.web.pages.tp.TPPages;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


/**
 * Created by mbelyaev on 2/21/17.
 */
public class WebDriverTest {

    private PageServiceImpl pageService;

    @BeforeTest
    public void openBrowser() throws Exception {
        TPPages.openBrowser();
    }

    @AfterTest
    public void closeBrowser() throws Exception {
        TPPages.closeBrowser();
    }
}
