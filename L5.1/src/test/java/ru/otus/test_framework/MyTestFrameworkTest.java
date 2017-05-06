package ru.otus.test_framework;

import org.junit.*;

/**
 * Created by alex on 06.05.2017.
 */
public class MyTestFrameworkTest {

    @Test
    public void addPackage() {
        MyTestFramework myTestFramework = new MyTestFramework();
        org.junit.Assert.assertEquals("Wrong count of test classes",
                2,
                myTestFramework.addPackage("ru.otus.test_framework.test_data"));
    }
}
