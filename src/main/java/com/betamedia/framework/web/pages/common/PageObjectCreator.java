package com.betamedia.framework.web.pages.common;

import com.betamedia.framework.web.pages.common.entities.AbstractPageObject;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface PageObjectCreator {
     <T extends AbstractPageObject> T getPage(Class<T> clazz);
     void closeBrowser();
}
