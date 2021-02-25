package myjunit;

import myjunit.assertion.Assert;

import java.lang.reflect.Method;

public class TestCaseTest extends TestCase {

    public TestCaseTest(String testCaseName) {
        super(testCaseName);
    }

    @Override
    public void run() {
        try {
            Method method = this.getClass().getMethod(super.testCaseName, null);
            method.invoke(this, null);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void runTest () {
        long sum = 10+10;
        Assert.assertTrue(sum == 20);
    }

    public static void main(String[] args) {
        new TestCaseTest("runTest").run();
    }
}