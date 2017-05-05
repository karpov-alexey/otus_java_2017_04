package ru.otus.test_framework;


public final class Assert {

    public static void fail(String message) {
        throw new AssertionError(message);
    }

    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            fail(message);
        }
    }

    public static void assertNotNull(String message, Object obj) {
        assertTrue(message,obj != null);
    }

}