package ru.otus.my_test_framework;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * Created by Alexey on 03.05.2017.
 */
public class TestClass {
    public String className = null;

    TestClass(String className) {
        this.className = className;
    }

    public void run() throws ClassNotFoundException {
        Class<?> clazz = Class.forName(className);
        System.out.println(clazz.getCanonicalName());
        for (Method method : clazz.getMethods())
        {
            System.out.println(method);
            for (Annotation annotation : method.getAnnotations())
            {
                System.out.println(annotation);
            }
        }

    }
}
