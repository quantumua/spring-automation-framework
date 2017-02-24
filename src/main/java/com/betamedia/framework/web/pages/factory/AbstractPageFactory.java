package com.betamedia.framework.web.pages.factory;

import com.betamedia.framework.web.pages.common.PageObjectCreator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/23/17.
 */

public abstract class AbstractPageFactory implements PageFactory {
    @Autowired
    protected PageObjectCreator creator;

    @Override
    public void closeBrowser() {
        creator.closeBrowser();
    }
}
