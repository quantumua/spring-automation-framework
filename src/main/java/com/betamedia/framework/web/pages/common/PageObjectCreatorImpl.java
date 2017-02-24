package com.betamedia.framework.web.pages.common;

import com.betamedia.framework.components.WebElementRepository;
import com.betamedia.framework.web.pages.common.annotation.StoredId;
import com.betamedia.framework.web.pages.common.entities.AbstractPageObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;
import java.util.Map;

/**
 * Created by mbelyaev on 2/23/17.
 */
@Service
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

//    public LoginPage loginPage() {
//        return getPage(LoginPageImpl.class);
//    }
//
//    public TopNavigationPage topNavigationPage() {
//        return getPage(TopNavigationPageImpl.class);
//    }
//
//    public DisclaimerNotification disclaimerNotification() {
//        return getPage(DisclaimerNotificationImpl.class);
//    }
//
//    public LoginErrorNotification loginErrorNotification() {
//        return getPage(LoginErrorNotificationImpl.class);
//    }
//
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
//
//    private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
//        fields.addAll(Arrays.asList(type.getDeclaredFields()));
//        if (type.getSuperclass() != null) {
//            fields = getAllFields(fields, type.getSuperclass());
//        }
//        return fields;
//    }
}
