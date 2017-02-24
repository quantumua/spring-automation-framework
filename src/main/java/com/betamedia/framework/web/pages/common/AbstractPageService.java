package com.betamedia.framework.web.pages.common;

import com.betamedia.framework.components.WebElementRepository;
import com.betamedia.framework.web.pages.common.annotation.StoredId;
import com.betamedia.framework.web.pages.tp.login.DisclaimerNotification;
import com.betamedia.framework.web.pages.tp.login.LoginErrorNotification;
import com.betamedia.framework.web.pages.tp.login.LoginPage;
import com.betamedia.framework.web.pages.tp.login.impl.DisclaimerNotificationImpl;
import com.betamedia.framework.web.pages.tp.login.impl.LoginErrorNotificationImpl;
import com.betamedia.framework.web.pages.tp.login.impl.LoginPageImpl;
import com.betamedia.framework.web.pages.tp.navigation.TopNavigationPage;
import com.betamedia.framework.web.pages.tp.navigation.TopNavigationPageImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by mbelyaev on 2/23/17.
 */
public abstract class AbstractPageService {

    protected WebDriver driver;
    protected WebElementRepository repository;

    protected <T> T getPage(Class<T> clazz) {
        T page;
        try {
            page = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        Map<String, String> locations = repository.getLocations(clazz.getSimpleName());
        initWebFields(page, locations);
        return page;
    }

    private <T> void initWebFields(T page, Map<String, String> locations) {
        List<Field> fields = getAllFields(new LinkedList<>(), page.getClass());
        for (Field field : fields) {
            if (field.getName().equals("driver")) {
                setField(field, page, driver);
            } else if (field.getName().equals("repository")) {
                setField(field, page, repository);
            } else if (field.isAnnotationPresent(StoredId.class) && field.getType().isAssignableFrom(By.class)) {
                StoredId storedId = field.getAnnotation(StoredId.class);
                setField(field, page, By.id(locations.get(storedId.value())));
            }
        }
    }

    public LoginPage loginPage() {
        return getPage(LoginPageImpl.class);
    }

    public TopNavigationPage topNavigationPage() {
        return getPage(TopNavigationPageImpl.class);
    }

    public DisclaimerNotification disclaimerNotification() {
        return getPage(DisclaimerNotificationImpl.class);
    }

    public LoginErrorNotification loginErrorNotification() {
        return getPage(LoginErrorNotificationImpl.class);
    }

    public void closeBrowser() {
        driver.quit();
    }

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

    private static List<Field> getAllFields(List<Field> fields, Class<?> type) {
        fields.addAll(Arrays.asList(type.getDeclaredFields()));
        if (type.getSuperclass() != null) {
            fields = getAllFields(fields, type.getSuperclass());
        }
        return fields;
    }
}
