package ru.otus.test_framework.test_data;

import ru.otus.test_framework.annotations.After;
import ru.otus.test_framework.annotations.Before;
import ru.otus.test_framework.annotations.Ignore;
import ru.otus.test_framework.annotations.Test;

/**
 * Created by Alexey on 05.05.2017.
 */
public class CommonTestClass {

    @Before
    public void before() {

    }

    @After
    public void after() {

    }

    @Test
    public void test1() {

    }

    @Test
    public void test2() {

    }

    @Test
    @Ignore
    public void ignoreTest() {

    }

    public void func() {

    }

}
