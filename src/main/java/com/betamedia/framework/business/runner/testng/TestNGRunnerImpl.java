package com.betamedia.framework.business.runner.testng;

import com.betamedia.framework.business.runner.TestRunner;
import com.betamedia.framework.tests.LoginPageTest;
import org.springframework.stereotype.Component;
import org.testng.ITestNGListener;
import org.testng.TestListenerAdapter;
import org.testng.TestNG;
import org.testng.xml.XmlSuite;

/**
 * @author Maksym Tsybulskyy
 *         Date: 2/24/17.
 */
@Component
public class TestNGRunnerImpl implements TestRunner {
    @Override
    public boolean isAssignable(String[] args) {
        return true;
    }

    @Override
    public void run(String[] args) {
        ITestNGListener tla = new TestListenerAdapter();
        TestNG testng = new TestNG();
        testng.setTestClasses(new Class[]{LoginPageTest.class});
        testng.addListener(tla);
        testng.setParallel(XmlSuite.ParallelMode.METHODS);
        testng.setThreadCount(5);
        testng.run();
    }
}
