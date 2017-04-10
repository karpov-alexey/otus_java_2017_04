package ru.otus;

import ru.otus.factory.ClassFactory;
import ru.otus.factory.FactoryException;
import ru.otus.factory.ObjectFactory;
import java.util.Arrays;


/**
 * Created by Alexey on 08.04.2017.
 */
public class ObjectSize
{

    static void calculatecalculateByMemoryAllocation(Class<?> type, int objectCount, int measureCount) throws FactoryException, InterruptedException {
        ObjectFactory factory = new ClassFactory(type);
        calculatecalculateByMemoryAllocation(factory, objectCount, measureCount);
    }

    static void calculatecalculateByMemoryAllocation(String className, int objectCount, int measureCount) throws FactoryException, InterruptedException, ClassNotFoundException {
        ObjectFactory factory = new ClassFactory(Class.forName(className));
        calculatecalculateByMemoryAllocation(factory, objectCount, measureCount);
    }

    static void calculatecalculateByMemoryAllocation(ObjectFactory factory, int objectCount, int measureCount) throws FactoryException, InterruptedException {
        System.out.println("Calculated object size for class " + factory.getObjectClass().getCanonicalName());
        for (int i = 0; i < measureCount; ++i) {

            Object[] objects = new Object[objectCount];

            System.gc();
            Thread.sleep(1000);
            //System.out.println("Before allocate");
            //printMemoryInfo();

            long usedMemoryBefore = usedMemory();
            //printMemoryInfo();

            System.out.println("Allocating memory. Waiting...");

            for (int j = 0; j < objectCount; ++j) {
                objects[j] = factory.createObject();
            }

//            printMemoryInfo();
            System.gc();
            Thread.sleep(1000);
//            printMemoryInfo();

            long usedMemoryAfter = usedMemory();
            double size = (usedMemoryAfter - usedMemoryBefore) / (double) objectCount;
            System.out.println("Used memory: before = " + usedMemoryBefore + ", after = " + usedMemoryAfter);
            System.out.println(i + ". Size of " + factory.getObjectClass().getCanonicalName() + " (calculated by memory allocation) = " + size);
            if (objectCount == 0) {
                objects[0] = null;
            }
            System.out.println("Clear memory. Waiting...");
        }
    }

    static long printMemoryInfo() {
        System.out.println("=== Memory info =======");
        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory(); // current heap allocated to the VM process
        System.out.println("totalMemory = " + totalMemory);
        long freeMemory = runtime.freeMemory(); // out of the current heap, how much is free
        System.out.println("freeMemory = " + freeMemory);
        long maxMemory = runtime.maxMemory(); // Max heap VM can use e.g. Xmx setting
        System.out.println("maxMemory = " + maxMemory);
        long usedMemory = totalMemory - freeMemory; // how much of the current heap the VM is using
        System.out.println("usedMemory = " + usedMemory);
        long availableMemory = maxMemory - usedMemory; // available memory i.e. Maximum heap size minus the current amount used
        System.out.println("availableMemory = " + availableMemory);
        System.out.println();
        return availableMemory;
    }


    static long usedMemory()
    {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }


    static void calculateByJavaAgentInstrument(Class<?> type) throws FactoryException {
        ObjectFactory factory = new ClassFactory(type);
        calculateByJavaAgentInstrument(factory);
    }

    static void calculateByJavaAgentInstrument(String className) throws ClassNotFoundException, FactoryException {
        ObjectFactory factory = new ClassFactory(Class.forName(className));
        calculateByJavaAgentInstrument(factory);
    }

    static void calculateByJavaAgentInstrument(ObjectFactory factory) throws FactoryException {
        try {
            long size = JavaAgent.getObjectSize(factory.createObject());
            System.out.println("Size of " +  factory.getObjectClass().getCanonicalName() + " (calculated by java agent instrument) = " + size);
        }
        catch (JavaAgent.NonInitializeJavaAgent ex) {
            System.out.println("Java agent is not initialized. Run application: java -javaagent:object_size.jar -jar object_size.jar");
        }
    }
}
