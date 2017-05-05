package ru.otus.test_framework;


import org.junit.Test;
import ru.otus.test_framework.exceptions.TestFrameworkException;
import ru.otus.test_framework.test_data.CommonTestClass;

import java.lang.reflect.InvocationTargetException;

import static org.mockito.Mockito.*;

public class TestCaseExecutorTest {
    @Test
    public void runExecutor() throws TestFrameworkException, IllegalAccessException, InvocationTargetException, InstantiationException {
        CommonTestClass testClass = spy(new CommonTestClass());
        TestCaseExecutor testCaseExecutor = spy(new TestCaseExecutor(new TestCase("ru.otus.test_framework.test_data.CommonTestClass")));
        when(testCaseExecutor.createInstance()).thenReturn(testClass);
        testCaseExecutor.run();
        verify(testClass, times(2)).after();
        verify(testClass, times(2)).before();
        verify(testClass, times(1)).test1();
        verify(testClass, times(1)).test2();
        verify(testCaseExecutor, times(2)).createInstance();
    }
}
