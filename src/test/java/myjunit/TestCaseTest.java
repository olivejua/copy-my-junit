package myjunit;

import myjunit.assertion.Assert;
import myjunit.result.TestResult;

public class TestCaseTest extends TestCase {

    public TestCaseTest(String testCaseName) {
        super(testCaseName);
    }

    private static long base;

    @Override
    public void before() {
        base = 10;
    }

    public void runTest () {
        long sum = 10+base;
        Assert.assertTrue(sum == 30);
    }

    public void runTestMinus() {
        long minus = 100-base;
        Assert.assertTrue(minus == 90);
    }

    public static void main(String[] args) {
        TestSuite testSuite = new TestSuite();
        testSuite.addTestCase(new TestCaseTest("runTest"));
        testSuite.addTestCase(new TestCaseTest("runTestMinus"));

        TestResult testResult = new TestResult();
        testSuite.run(testResult);

        testResult.printCount();
    }
}