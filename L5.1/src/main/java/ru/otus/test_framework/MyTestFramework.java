package ru.otus.test_framework;

import java.util.ArrayList;
import java.util.List;


public class MyTestFramework {

    List<TestCase> testCases = new ArrayList<>();

    public  MyTestFramework(String[] args) throws Exception {
        for (String className : args)
        {
            testCases.add(new TestCase(className));
        }
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
}
