package ru.otus;

/**
 * Created by Alexey on 08.04.2017.
 */
public class ObjectSize
{
    private Class<?> type;
    private final int count;

    Object[] objects;

    ObjectSize(Class<?> type, int count)
    {
        this.type = type;
        this.count = count;
        objects = new Object[count];
    }

    long getAvailableMemory() {
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

    void calculateByMemoryAllocationInstrument(int cycleCount) throws IllegalAccessException, InstantiationException, InterruptedException {
        for (int i = 0; i < cycleCount; ++i) {
            objects = new Object[count];
            System.gc();
            Thread.sleep(10000);
            System.out.println("Before allocate");
            long before = getAvailableMemory();

            for (int j = 0; j < objects.length; ++j) {
                objects[j] = type.newInstance();
            }

            Thread.sleep(1000);

            System.out.println("After allocate");
            long after = getAvailableMemory();
            double size = (before - after) / (double) count;
            System.out.println("Size of " + type.getCanonicalName() + " (calculated by memory allocation instrument) = " + size);
        }
    }

    void calculateByJavaAgentInstrument() throws IllegalAccessException, InstantiationException, InterruptedException {
        try {
            long size = JavaAgent.getObjectSize(type.newInstance());
            System.out.println("Size of " +  type.getCanonicalName() + " (calculated by java agent instrument) = " + size);
        }
        catch (JavaAgent.NonInitializeJavaAgent ex) {
            System.out.println("Java agent is not initialized. Run application: java -javaagent:object_size.jar -jar object_size.jar");
        }
    }
}
