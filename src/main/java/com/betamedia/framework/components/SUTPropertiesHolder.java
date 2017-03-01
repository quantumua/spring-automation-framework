package com.betamedia.framework.components;

import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */
public interface SUTPropertiesHolder {
    public final String CHROME_DRIVER_PATH = "chrome.driver.path";
    public final String REMOTE_DRIVER_URL ="remote.driver.url";
    public static final String DOMAIN_URL = "domain.url";
    <T> T get(String key);
    void set(Properties properties);
}
