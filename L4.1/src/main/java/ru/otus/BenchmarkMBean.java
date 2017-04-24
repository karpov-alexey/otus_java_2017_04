package ru.otus;

/**
 * Created by Alexey on 24.04.2017.
 */
public interface BenchmarkMBean {
    int getObjectCount();
    void setObjectCount(int count);

    int getCycleCount();
    void setCycleCount(int count);
}