package com.betamedia.framework.web.pages.common.impl;

import com.betamedia.framework.components.WebElementRepository;
import com.betamedia.framework.web.pages.common.AbstractPageService;
import org.openqa.selenium.WebDriver;

/**
 * Created by mbelyaev on 2/23/17.
 */
public class PageServiceImpl extends AbstractPageService {
    public PageServiceImpl(WebDriver driver, WebElementRepository repository) {
        this.driver = driver;
        this.repository = repository;
        driver.get("https://qawww.24option.com/eu/trade/");
    }
}
