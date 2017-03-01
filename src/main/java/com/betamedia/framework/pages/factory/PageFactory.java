package com.betamedia.framework.pages.factory;

import com.betamedia.framework.pages.common.type.AppType;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface PageFactory<E extends AppType> {

    E getType();

    void closeBrowser();

}
