package ru.otus.test_framework;

import org.junit.Test;

public class AssertTest {
    @Test(expected = java.lang.AssertionError.class)
    public void assertFail() {
        Assert.fail("fail assert");
    }

    @Test
    public void assertTrue_Ok() {
        Assert.assertTrue("value is not true", true);
    }

    @Test(expected = java.lang.AssertionError.class)
    public void assertTrue_False() {
        Assert.assertTrue("assert is not false", false);
    }

    @Test
    public void assertNotNull_Ok() {
        Assert.assertNotNull("value is null", new Object());
    }

    @Test(expected = java.lang.AssertionError.class)
    public void assertNotNull_False() {
        Assert.assertNotNull("value is ot null", null);
    }
}
