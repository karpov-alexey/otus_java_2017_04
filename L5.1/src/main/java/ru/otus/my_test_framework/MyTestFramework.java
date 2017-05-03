package ru.otus.my_test_framework;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Alexey on 03.05.2017.
 */
public class MyTestFramework {

    List<String> classNames = new ArrayList<>();
    public  MyTestFramework(String[] args) {
        classNames.addAll(Arrays.asList(args));
    }

    public void run() throws Exception {

        if (classNames.isEmpty())
        {
            throw new Exception("Tests not found");
        }

        for (String className : classNames){
            new TestClass(className).run();
        }
    }
}
