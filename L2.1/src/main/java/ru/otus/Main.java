package ru.otus;

import ru.otus.factory.FactoryException;
import ru.otus.factory.NewObjectFactory;
import ru.otus.factory.ObjectFactory;

import java.lang.management.ManagementFactory;

/**
 * Created by Alexey on 01.04.2017.
 */



class StringFactory implements ObjectFactory {
    @Override
    public Object createObject() {
        return new String("Hello world");
    }

    @Override
    public Class<?> getObjectClass() {
        return String.class;
    }
}


public class Main  {

    public static void main(String[] args) throws FactoryException, InterruptedException, ClassNotFoundException {

        System.out.println("Running application with pid: " + ManagementFactory.getRuntimeMXBean().getName());
        int objectCount = 1_000_000;

        if (args.length > 0)
        {
            String className = args[0];
            System.out.println("\n=== Example 1. Class from command line: " + className + " ===");
            ObjectSize.calculatecalculateByMemoryAllocation(className, objectCount, 1);
        }

        System.out.println("=== Example 2. Empty class String ===");
        ObjectSize.calculatecalculateByMemoryAllocation(String.class, objectCount, 3);

        System.out.println("=== Example 3. My test class ===");
        ObjectSize.calculatecalculateByMemoryAllocation(new TestFactory(), objectCount, 1);

        System.out.println("=== Example 4. String class with text \"Hello world\" ===");
        ObjectSize.calculatecalculateByMemoryAllocation(new StringFactory(), objectCount, 1);

        System.out.println("=== Example 5. Dynamic create string class with text \"Hello world\" ===");
        ObjectSize.calculatecalculateByMemoryAllocation(new NewObjectFactory(String.class, new Class<?>[]{String.class}, new String[]{"Hello world"}), objectCount, 1);
    }
}
