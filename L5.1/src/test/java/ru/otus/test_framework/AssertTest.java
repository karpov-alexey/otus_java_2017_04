package ru.otus.test_framework;

import org.junit.Test;

/**
 * Created by Alexey on 05.05.2017.
 */
public class AssertTest {
    @Test(expected = java.lang.AssertionError.class)
    public void assertFail() {
        Assert.assertFail("fail as expected");
    }


    @Test(expected = java.lang.AssertionError.class)
    public void assertTrue_false() {
        Assert.assertTrue("test Assert true", false);
    }

    @Test
    public void assertTrue() {
        Assert.assertTrue("test Assert true", true);
    }


    @Test(expected = java.lang.AssertionError.class)
    public void assertNotNull_null() {
        Assert.assertNotNull("test Assert null", null);
    }

    @Test
    public void assertNotNull() {
        Assert.assertNotNull("test Assert null", "notNull");
    }
}
