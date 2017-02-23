package com.betamedia.framework.components;

import com.betamedia.framework.components.impl.WebElementRepository;
import com.betamedia.framework.web.pages.common.annotation.StoredId;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Created by mbelyaev on 2/23/17.
 */
public abstract class AbstractPageService {

    abstract protected WebDriver getDriver();

    abstract protected WebElementRepository getRepository();

    protected <T> T getPage(Class<T> clazz) {
        T page;
        try {
            page = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Map<String, String> locations = getRepository().getLocations(clazz.getSimpleName());
        initWebFields(page, locations);
        return page;
    }

    private <T> void initWebFields(T page, Map<String, String> locations) {
        Field[] declaredFields = page.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(StoredId.class) && field.getType().isAssignableFrom(By.class)) {
                StoredId storedId = field.getAnnotation(StoredId.class);
                try {
                    if (Modifier.isPrivate(field.getModifiers())) {
                        field.setAccessible(true);
                    }
                    field.set(page, By.id(locations.get(storedId.value())));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
