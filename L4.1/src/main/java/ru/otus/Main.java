package ru.otus;


import javax.management.*;
import java.lang.management.ManagementFactory;

/**
 * Created by Alexey on 01.04.2017.
 */

public class Main  {

    public static void main(String[] args) throws NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException, MalformedObjectNameException {

        System.out.println("Starting application pid: " + ManagementFactory.getRuntimeMXBean().getName());

        int objectCount = 0;
        int cycleCount = 0;
        if (args.length == 2)
        {
            objectCount = Integer.parseInt(args[0]);
            cycleCount = Integer.parseInt(args[1]);
        }
        else {
            objectCount = 1 * 1000 * 1000;
            cycleCount = 100;
        }
        System.out.println("objectCount = " + objectCount + "    cycleCount = " + cycleCount);

        MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
        ObjectName name = new ObjectName("ru.otus:type=Benchmark");
        Benchmark mbean = new Benchmark();
        mbs.registerMBean(mbean, name);

        mbean.setCycleCount(cycleCount);
        mbean.setObjectCount(objectCount);
        mbean.run();

    }
}
