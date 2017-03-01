package com.betamedia.framework.pages.factory;

import com.betamedia.framework.entities.page.AbstractPageObject;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface PageObjectCreator {
     <T extends AbstractPageObject> T getPage(Class<T> clazz);
     void closeBrowser();
}
