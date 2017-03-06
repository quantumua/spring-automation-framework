package com.betamedia.framework.configuration;

import com.betamedia.framework.components.SUTPropertiesHolder;
import com.betamedia.framework.components.SUTPropertiesHolderImpl;
import org.springframework.context.annotation.*;

import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */

@Configuration
public class PerRequestConfiguration {

    @Bean
    @Lazy
    @Scope(scopeName = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
    public SUTPropertiesHolder sutPropertiesHolder(Properties properties) {
        return new SUTPropertiesHolderImpl(properties);
    }
}
