package com.betamedia.framework.web.pages.factory;

import com.betamedia.framework.web.pages.common.type.AppType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface PageFactory<E extends AppType> {

    E getType();

    void closeBrowser();

}
