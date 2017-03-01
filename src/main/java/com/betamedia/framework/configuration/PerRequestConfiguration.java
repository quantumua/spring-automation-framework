package com.betamedia.framework.configuration;

import com.betamedia.framework.components.SUTPropertiesHolder;
import com.betamedia.framework.components.SUTPropertiesHolderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.context.annotation.RequestScope;

import java.util.Properties;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/28/17.
 */

@Configuration
@Lazy
@RequestScope
public class PerRequestConfiguration {

    @Bean
    public SUTPropertiesHolder sutPropertiesHolder(Properties properties) {
        return new SUTPropertiesHolderImpl(properties);
    }
}
