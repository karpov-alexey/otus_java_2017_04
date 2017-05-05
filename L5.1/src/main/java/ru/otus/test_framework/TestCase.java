package ru.otus.test_framework;

import ru.otus.test_framework.annotations.After;
import ru.otus.test_framework.annotations.Before;
import ru.otus.test_framework.annotations.Test;
import ru.otus.test_framework.exceptions.TestFrameworkException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class TestCase {
    private Class<?> clazz = null;
    private Method beforeMethod = null;
    private Method afterMethod = null;
    private List<Method> testMethods = null;

    public TestCase(String className) throws TestFrameworkException {
        try {
            clazz = Class.forName(className);
        } catch (Exception e) {
            TestFrameworkException exception = new TestFrameworkException("Can't found test class: " + className);
            exception.initCause(e);
            throw exception;
        }

    }

    public Class<?> getTestClass()
    {
        return clazz;
    }


    public List<Method> getTestMethods() {
        if (testMethods != null)
            return testMethods;

        testMethods = getMethods(Test.class);

        return testMethods;
    }

    public Method getBeforeMethod() throws TestFrameworkException {
        if (beforeMethod != null)
            return beforeMethod;

        List<Method> beforeMethods = getMethods(Before.class);

        if (beforeMethods.isEmpty()) {
            beforeMethod = null;
        } else if (beforeMethods.size() == 1) {
            beforeMethod = beforeMethods.get(0);

        } else {
            beforeMethod = null;
            throw new TestFrameworkException("Too more before methods: " + beforeMethods.size());
        }

        return beforeMethod;
    }

    public Method getAfterMethod() throws TestFrameworkException {
        if (afterMethod != null)
            return afterMethod;

        List<Method> afterMethods = getMethods(After.class);

        if (afterMethods.isEmpty()) {
            afterMethods = null;
        } else if (afterMethods.size() == 1) {
            afterMethod = afterMethods.get(0);
        } else {
            afterMethod = null;
            throw new TestFrameworkException("Too more after methods: " + afterMethods.size());
        }

        return afterMethod;
    }

    private List<Method> getMethods(Class<? extends Annotation> annotationClass)
    {
        return Arrays.stream(clazz.getMethods())
                .filter(method -> method.isAnnotationPresent(annotationClass))
                .collect(Collectors.toList());
    }
}
