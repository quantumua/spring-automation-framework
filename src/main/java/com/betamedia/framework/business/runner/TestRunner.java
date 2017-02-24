package com.betamedia.framework.business.runner;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestRunner {

    boolean isAssignable(String[] args);

    void run(String[] args);
}
