package ru.otus.test_framework;

import ru.otus.test_framework.exceptions.TestFrameworkException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestCaseExecutor {

    TestCase testCase = null;


    public TestCaseExecutor(TestCase testCase)
    {
        this.testCase = testCase;
    }

    public void run() throws TestFrameworkException, IllegalAccessException, InstantiationException, InvocationTargetException {
        try {

            for (Method testMethod : testCase.getTestMethods()) {
                Object instance = createInstance();
                runBefore(instance, testCase.getBeforeMethod());
                runTest(instance, testMethod);
                runAfter(instance, testCase.getAfterMethod());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }

    }

    public Object createInstance() throws IllegalAccessException, InstantiationException {
        return testCase.getTestClass().newInstance();
    }

    private void runBefore(Object obj, Method beforeMethod) throws InvocationTargetException, IllegalAccessException {
        if (beforeMethod != null) {
            beforeMethod.invoke(obj);
        }
    }

    private void runAfter(Object obj, Method afterMethod) throws InvocationTargetException, IllegalAccessException {
        if (afterMethod != null) {
            afterMethod.invoke(obj);
        }
    }

    private void runTest(Object obj, Method testMethod) throws InvocationTargetException, IllegalAccessException {
        if (testMethod != null) {
            testMethod.invoke(obj);
        }
    }

}
