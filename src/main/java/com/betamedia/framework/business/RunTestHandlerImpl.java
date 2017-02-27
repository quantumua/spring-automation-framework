package com.betamedia.framework.business;

import com.betamedia.framework.business.runner.TestRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Service
public class RunTestHandlerImpl implements RunTestHandler {

    @Autowired
    private List<TestRunner> runners;

    @Override
    public void handle() {
        runners.stream().filter(TestRunner::isAssignable)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No corresponding runner"))
                .run();
    }
}
