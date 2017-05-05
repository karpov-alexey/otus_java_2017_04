package ru.otus.example;

import ru.otus.test_framework.annotations.After;
import ru.otus.test_framework.annotations.Before;
import ru.otus.test_framework.annotations.Test;

public class ExampleClassTest {

    @Before
    public void beforeMethod1()
    {
        System.out.println("Running before method");
    }


    @After
    public void afterMethod1()
    {
        System.out.println("Running after method");
    }

    @Test
    public void testMethod1()
    {
        System.out.println("Running test method");
    }
}
