package com.betamedia.framework.pages.factory;

import com.betamedia.framework.components.WebElementRepository;
import com.betamedia.framework.pages.common.annotation.StoredId;
import com.betamedia.framework.entities.page.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Service
@Lazy
@Scope("prototype")
public class PageObjectCreatorImpl implements PageObjectCreator{

    private WebDriver driver;
    private WebElementRepository repository;

    @Autowired
    public PageObjectCreatorImpl(WebDriver driver, WebElementRepository repository) {
        this.driver = driver;
        this.repository = repository;
    }

    public  <T extends AbstractPageObject> T getPage(Class<T> clazz) {
        T page;
        try {
            page = clazz.getConstructor(WebDriver.class).newInstance(driver);
        } catch (InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Map<String, String> locations = repository.getLocations(clazz.getSimpleName());
        initWebFields(page, locations);
        return page;
    }

    private <T extends AbstractPageObject> void initWebFields(T page, Map<String, String> locations) {
        Field[] declaredFields = page.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            if (field.isAnnotationPresent(StoredId.class) && field.getType().isAssignableFrom(By.class)) {
                StoredId storedId = field.getAnnotation(StoredId.class);
                setField(field, page, By.id(locations.get(storedId.value())));
            }
        }
    }

    public void closeBrowser() {
        driver.quit();
    }
//
    private void setField(Field field, Object object, Object value) {
        try {
            if (Modifier.isPrivate(field.getModifiers())) {
                field.setAccessible(true);
            }
            field.set(object, value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
