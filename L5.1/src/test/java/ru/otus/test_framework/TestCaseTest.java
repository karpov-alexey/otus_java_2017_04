package ru.otus.test_framework;

import org.junit.*;
import ru.otus.test_framework.exceptions.TestFrameworkException;

public class TestCaseTest {
    @Test
    public void getZeroAfterMethods() throws TestFrameworkException {
        TestCase testCase = new TestCase("ru.otus.test_framework.test_data.EmptyTestClass");
        org.junit.Assert.assertNull("after method found", testCase.getAfterMethod());
    }

    @Test
    public void getOneAfterMethod() throws TestFrameworkException {
        TestCase testCase = new TestCase("ru.otus.test_framework.test_data.CommonTestClass");
        org.junit.Assert.assertNotNull("after method not found", testCase.getAfterMethod());
    }

    @Test(expected = ru.otus.test_framework.exceptions.TestFrameworkException.class)
    public void getManyAfterMethod() throws TestFrameworkException {
        TestCase testCase = new TestCase("ru.otus.test_framework.test_data.WrongTestClass");
        testCase.getAfterMethod();
    }


    @Test
    public void getZeroBeforeMethods() throws TestFrameworkException {
        TestCase testCase = new TestCase("ru.otus.test_framework.test_data.EmptyTestClass");
        org.junit.Assert.assertNull("before methods found", testCase.getBeforeMethod());
    }

    @Test
    public void getOneBeforerMethod() throws TestFrameworkException {
        TestCase testCase = new TestCase("ru.otus.test_framework.test_data.CommonTestClass");
        org.junit.Assert.assertNotNull("before method not found", testCase.getAfterMethod());
    }

    @Test(expected = ru.otus.test_framework.exceptions.TestFrameworkException.class)
    public void getManyBeforeMethod() throws TestFrameworkException {
        TestCase testCase = new TestCase("ru.otus.test_framework.test_data.WrongTestClass");
        testCase.getBeforeMethod();
    }


    @Test
    public void getZeroTestMethods() throws TestFrameworkException {
        TestCase testCase = new TestCase("ru.otus.test_framework.test_data.EmptyTestClass");
        org.junit.Assert.assertEquals("counter test methods", 0, testCase.getTestMethods().size());
    }

    @Test
    public void getManyTestMethods() throws TestFrameworkException {
        TestCase testCase = new TestCase("ru.otus.test_framework.test_data.CommonTestClass");
        org.junit.Assert.assertEquals("counter test methods", 3, testCase.getTestMethods().size());
        //check name
    }


}
