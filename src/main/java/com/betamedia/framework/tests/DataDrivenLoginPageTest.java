package com.betamedia.framework.tests;


import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */

public class DataDrivenLoginPageTest extends DataDrivenTPWebDriverTest{

    @Test(dataProvider = "authentication")
    public void loginTest(String username, String password) {
        pages().topNavigationPage().logIn();
        pages().loginPage().login(username, password);
        pages().disclaimerNotification().accept();
        assertTrue(pages().topNavigationPage().isLoggedIn());
    }

   /* @Test
    @TestProperties(name = "login with externally set username/password: username=${username}, password=${password}",
            paramsInclude = {"username", "password"})
    public void loginWithExternalParams() {
        TPPages.loginPage().goTo().login(username, password);
        TPPages.loginErrorNotification().dismiss();
    }

    @Test
    @TestProperties(name = "check if  login success with hardcoded user data", paramsInclude = {""})
    public void loginTest() {
        report.report("Start successful login test");
        TPPages.loginPage().goTo().login("vasichka", "123123");
        TPPages.disclaimerNotification().accept();
        assertThat(TPPages.topNavigationPage().isLoggedIn(), is(true));
        report.report("Finish successful login test");
    }

    @Test
    @TestProperties(name = "login failed with unauthorized user (hardcoded)", paramsInclude = {""})
    public void failedLoginTest() {
        TPPages.loginPage().goTo().login("randomname", "randompassword");
        TPPages.loginErrorNotification().dismiss();
    }

    @ParameterProperties(description = "username for login")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @ParameterProperties(description = "password for login")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }*/
}
