package ru.otus.example;

import ru.otus.test_framework.Assert;
import ru.otus.test_framework.annotations.After;
import ru.otus.test_framework.annotations.Before;
import ru.otus.test_framework.annotations.Test;

public class ExampleClassTest {

    ExampleClass exampleClass = null;

    @Before
    public void beforeMethod()
    {
        System.out.println("Running before method");
        exampleClass = new ExampleClass();
    }


    @After
    public void afterMethod()
    {
        System.out.println("Running after method");
        exampleClass = null;
    }

    @Test
    public void addString()
    {
        System.out.println("Running test method addString()");
        Assert.assertNotNull("ExampleClass is null", exampleClass);
        Assert.assertTrue("value not added", exampleClass.add("Hello"));
        Assert.assertTrue("value not added", exampleClass.add("World"));
    }

    @Test
    public void getSize()
    {
        System.out.println("Running test method");
        Assert.assertNotNull("ExampleClass is null", exampleClass);
        Assert.assertTrue("value not added", exampleClass.add("Hello"));
        Assert.assertTrue("value not added", exampleClass.add("World"));
        Assert.assertTrue("size of ExampleClass != 2", exampleClass.getSize() == 2);
    }
}
