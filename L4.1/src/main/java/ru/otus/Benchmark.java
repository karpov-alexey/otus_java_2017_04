package ru.otus;

/**
 * Created by Alexey on 24.04.2017.
 */
/**
 * Created by tully.
 */
class Benchmark implements BenchmarkMBean {
    private int objectCount = 0;
    private int cycleCount = 0;


    void run() {

        GCStatistics gcStatistics = new GCStatistics();
        Object[] objects = new Object[objectCount];
        int n = 0;
        for (int currentCycle = 0; currentCycle < cycleCount; ++currentCycle)
        {
            for (int currentObject = 0; currentObject < objectCount; ++currentObject)
            {
                objects[currentObject] = new String(new char[0]);
                ++n;
            }

            //logs(n, currentCycle);
            objects = new Object[objectCount];
        }

        gcStatistics.printStatistics();

    }

    @Override
    public int getObjectCount() {
        return objectCount;
    }

    @Override
    public void setObjectCount(int count) {
        objectCount = count;
    }

    @Override
    public int getCycleCount() {
        return cycleCount;
    }

    @Override
    public void setCycleCount(int count) {
        cycleCount = count;
    }

    private void logs(int n, int currentCycle) {
        System.out.println();
        System.out.println("Current cycle: " + currentCycle);
        System.out.println("Created " + n + " objects");
        System.out.println("Creating new array of size: " + objectCount);
    }
}