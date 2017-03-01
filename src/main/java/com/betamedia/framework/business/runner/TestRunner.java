package com.betamedia.framework.business.runner;

import com.betamedia.framework.entities.web.RunTestParams;

import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface TestRunner {

    boolean isAssignable(RunTestParams params);

    void run(List<String> suitesFileNames);
}
