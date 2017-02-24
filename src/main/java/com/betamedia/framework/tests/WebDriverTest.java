package com.betamedia.framework.tests;


import com.betamedia.framework.Application;
import com.betamedia.framework.components.WebElementRepository;
import com.betamedia.framework.web.pages.common.impl.PageServiceImpl;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;


/**
 * Created by mbelyaev on 2/21/17.
 */
public class WebDriverTest {

    protected PageServiceImpl pageService;

    @BeforeMethod
    public void setUp() throws Exception {
        pageService = new PageServiceImpl(Application.getBean(WebDriver.class), Application.getBean(WebElementRepository.class));
    }

    @AfterMethod
    public void tearDown() throws Exception {
        pageService.closeBrowser();
    }
    
}
