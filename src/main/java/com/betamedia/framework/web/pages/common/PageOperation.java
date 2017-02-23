package com.betamedia.framework.web.pages.common;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/15/17.
 */
public abstract class PageOperation<T> extends Location {
    abstract public T goTo();
}
