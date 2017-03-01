package com.betamedia.framework.business;

import com.betamedia.framework.entities.web.RunTestParams;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
public interface RunTestHandler {
    void handle(RunTestParams params);
}
