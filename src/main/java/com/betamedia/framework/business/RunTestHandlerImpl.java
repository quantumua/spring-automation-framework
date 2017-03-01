package com.betamedia.framework.business;

import com.betamedia.framework.business.runner.TestRunner;
import com.betamedia.framework.entities.web.RunTestParams;
import org.springframework.beans.factory.annotation.Autowired;
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
    public void handle(RunTestParams params) {
        runners.stream().filter(runner->runner.isAssignable(params))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No corresponding runner"))
                .run(params.getSuite());
    }
}
