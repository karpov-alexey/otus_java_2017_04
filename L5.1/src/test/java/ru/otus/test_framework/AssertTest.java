package ru.otus.test_framework;

import org.junit.Test;

/**
 * Created by Alexey on 05.05.2017.
 */
public class AssertTest {
    @Test(expected = java.lang.AssertionError.class)
    public void assertFail() {
        Assert.fail("fail assert");
    }

    @Test
    public void assertTrue_true() {
        Assert.assertTrue("assert true", true);
    }

    @Test(expected = java.lang.AssertionError.class)
    public void assertTrue_false() {
        Assert.assertTrue("assert false", false);
    }

    @Test(expected = java.lang.AssertionError.class)
    public void assertNotNull_Null() {
        Assert.assertNotNull("assert not null(false)", null);
    }

    @Test
    public void assertNotNull_noNull() {
        Assert.assertNotNull("assert not null (true)", "notNull");
    }
}
