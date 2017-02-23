package com.betamedia.framework.web.pages.tp.login;


import com.betamedia.framework.web.pages.common.PageOperation;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public abstract class LoginPage extends PageOperation<LoginPage> {
    abstract public void login(String username, String password);
}
