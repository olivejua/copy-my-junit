package myjunit;


import myjunit.assertion.AssertionFailedError;
import myjunit.result.TestResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public abstract class TestCase implements Test {

    private static final Logger logger = LoggerFactory.getLogger(TestCase.class);

    protected String testCaseName;

    public TestCase(String testCaseName) {
        this.testCaseName = testCaseName;
    }

    public TestResult run() {
        TestResult testResult = createTestResult();
        run(testResult);

        return testResult;
    }

    public void run(TestResult testResult) {
        testResult.startTest();
        before();
        try {
            runTestCase();
        } catch (InvocationTargetException ite) {
            if(isAssertionFailed(ite)) {
                testResult.addFailure(this);
            } else {
                testResult.addError(this, ite);
            }
        } catch (Exception e) {
            testResult.addError(this, e);
        }
        after();
    }

    private boolean isAssertionFailed(InvocationTargetException ite) {
        return ite.getTargetException() instanceof AssertionFailedError;
    }

    private TestResult createTestResult() {
        return new TestResult();
    }

    protected void before() {}

    private void runTestCase() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        logger.info("{} execute ", testCaseName);
        Method method = this.getClass().getMethod(testCaseName);
        method.invoke(this);
    }

    protected void after() {}

    public String getTestCaseName() {
        return testCaseName;
    }
}
