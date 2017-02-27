package com.betamedia.framework.business.runner.testng;

import com.betamedia.framework.business.runner.TestRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.stereotype.Component;
import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;

import java.util.List;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Component
public class TestNGRunnerImpl implements TestRunner {

    @Autowired
    private ApplicationArguments args;

    @Override
    public boolean isAssignable() {
        return !args.getNonOptionArgs().isEmpty();
    }

    @Override
    public void run() {
        //read files
        ITestNGListener tla = new TestListenerAdapter();
        TestNG testng = new TestNG();

        List<String> xmlNames = args.getNonOptionArgs();

        testng.setTestSuites(xmlNames);

        testng.addListener(tla);
        testng.run();
    }
}
