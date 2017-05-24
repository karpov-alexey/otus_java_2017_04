package ru.otus.exception;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Alexey on 16.05.2017.
 */
public class AtmExceptionTest {

    @Test
    public void exception() {
        try {
            throw new AtmException("test exception");
        } catch (AtmException e) {
            Assert.assertEquals("test exception", e.getMessage());
        }
    }

}