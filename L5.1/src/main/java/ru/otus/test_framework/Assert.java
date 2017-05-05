package ru.otus.test_framework;


public final class Assert {

    public static void assertFail(String message) {
        throw new AssertionError(message);
    }

    public static void assertTrue(String message, boolean statement) {
        if (!statement) {
            throw new AssertionError(message);
        }
    }

    public static void assertNotNull(String message, Object statement) {
        if (statement == null) {
            throw new AssertionError(message);
        }
    }
}