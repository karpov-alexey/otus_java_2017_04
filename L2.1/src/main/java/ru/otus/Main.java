package ru.otus;

import ru.otus.factory.FactoryException;
import ru.otus.factory.NewObjectFactory;
import ru.otus.factory.ObjectFactory;

import java.lang.management.ManagementFactory;
import java.util.ArrayList;

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

class StringArrayListFactory implements ObjectFactory {
    int count = 0;


    StringArrayListFactory(int count)
    {
        this.count = count;
    }
    @Override
    public Object createObject() {
        ArrayList<String> arrayList = new ArrayList<>(count);
        for (int i = 0; i < count; ++i)
        {
            arrayList.add(new String());
        }

        return arrayList;
    }

    @Override
    public Class<?> getObjectClass() {
        return ArrayList.class;
    }
}


public class Main  {

    public static void main(String[] args) throws FactoryException, InterruptedException, ClassNotFoundException {

        System.out.println("Running application with pid: " + ManagementFactory.getRuntimeMXBean().getName());
        int objectCount = 5_000_000;

        if (args.length > 0)
        {
            String className = args[0];
            System.out.println("\n=== Example 1. Class from command line: " + className + " ===");
            ObjectSize.calculatecalculateByMemoryAllocation(className, objectCount, 1);
        }

        System.out.println("\n=== Example 2. Empty class String ===");
        ObjectSize.calculatecalculateByMemoryAllocation(String.class, objectCount, 3);

        System.out.println("\n=== Example 3. My test class ===");
        ObjectSize.calculatecalculateByMemoryAllocation(new TestFactory(), objectCount, 1);

        System.out.println("\n=== Example 4. String class with text \"Hello world\" ===");
        ObjectSize.calculatecalculateByMemoryAllocation(new StringFactory(), objectCount, 1);

        System.out.println("\n=== Example 5. Dynamic create string class with text \"Hello world\" ===");
        ObjectSize.calculatecalculateByMemoryAllocation(new NewObjectFactory(String.class, new Class<?>[]{String.class}, new String[]{"Hello world"}), objectCount, 1);

        // Опеределяем зависимость размера объекта коллекции от количества элементов
        // Стараемся во всех экспериментах примерно выделять одинаковое количество памяти,
        // поэтому количество созданных коллекций зависит от числа элементов в них
        System.out.println("\n=== Example 6. Arraylist example (empty) ===" + "string in collection = 0");
        ObjectSize.calculatecalculateByMemoryAllocation(new StringArrayListFactory(0), objectCount, 1);

        for (int i = 1; i < objectCount; i *= 10) {
            System.out.println("\n=== Example 6. Arraylist example (empty) ===" + "string in collection = " + i);
            ObjectSize.calculatecalculateByMemoryAllocation(new StringArrayListFactory(i), objectCount / i, 1);
        }
    }
}
