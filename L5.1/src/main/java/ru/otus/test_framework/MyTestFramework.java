package ru.otus.test_framework;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.reflections.scanners.ResourcesScanner;
import org.reflections.scanners.SubTypesScanner;
import org.reflections.util.ClasspathHelper;
import org.reflections.util.ConfigurationBuilder;
import org.reflections.util.FilterBuilder;
import ru.otus.test_framework.annotations.Test;
import ru.otus.test_framework.exceptions.TestFrameworkException;

import java.lang.reflect.Method;
import java.util.*;
import java.util.stream.Collectors;


public class MyTestFramework {

    private List<TestCase> testCases = new ArrayList<>();

    public MyTestFramework() {
    }

    public int addPackage(String packageName){
        List<ClassLoader> classLoadersList = new LinkedList<ClassLoader>();
        classLoadersList.add(ClasspathHelper.contextClassLoader());
        classLoadersList.add(ClasspathHelper.staticClassLoader());

        Reflections reflections = new Reflections(new ConfigurationBuilder()
                .setScanners(new SubTypesScanner(false /* don't exclude Object.class */), new ResourcesScanner())
                .setUrls(ClasspathHelper.forClassLoader(classLoadersList.toArray(new ClassLoader[0])))
                .filterInputsBy(new FilterBuilder().include(FilterBuilder.prefix(packageName))));

        Set<Class<?>> classes = reflections.getSubTypesOf(Object.class);
        int addedClassCounter = 0;
        for (Class<?> clazz : classes)
        {
            try {
                if (!isTestClass(clazz))
                {
                    continue;
                }
                addClass(clazz);
            } catch (Exception e) {
                continue;
            }
            ++addedClassCounter;
        }
        return addedClassCounter;
    }

    public void addClass(String className) throws TestFrameworkException {
        if(className != null && !className.isEmpty())
            testCases.add(new TestCase(className));
    }

    public void addClass(Class<?> clazz) throws TestFrameworkException {
        if(clazz != null)
            testCases.add(new TestCase(clazz));
    }

    public void run() throws Exception {
        if (testCases.isEmpty())
        {
            System.out.println("Tests not found");
            return;
        }

        for (TestCase testCase : testCases){
            new TestCaseExecutor(testCase).run();
        }
    }

    private boolean isTestClass(Class<?> clazz)
    {
        try {
            for (Method method : clazz.getMethods())
            {
                if (method.isAnnotationPresent(Test.class))
                {
                    return true;
                }
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }
}
