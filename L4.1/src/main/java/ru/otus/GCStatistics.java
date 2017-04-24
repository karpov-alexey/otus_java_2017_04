package ru.otus;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.ListenerNotFoundException;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.*;
import java.util.concurrent.CountDownLatch;

/**
 * Created by Alexey on 24.04.2017.
 */

class Info
{
    public List<Long> durations = new ArrayList<>();
    public void add(Long duration)
    {
        durations.add(duration);
    }
}


public class GCStatistics {
    private final CountDownLatch doneSignal = new CountDownLatch(2);
    private List<Runnable> registration = new ArrayList<>();
    TreeMap<String, Info> infoMap = new TreeMap<>();


    public GCStatistics() {
        installGCMonitoring();
    }

    public void printStatistics()
    {
        for(Map.Entry<String,Info> entry : infoMap.entrySet()) {
            String name = entry.getKey();
            Info info = entry.getValue();

            System.out.println(name + " => " + info.durations);
        }
    }


    private void installGCMonitoring() {
        List<GarbageCollectorMXBean> gcbeans = java.lang.management.ManagementFactory.getGarbageCollectorMXBeans();

        for (GarbageCollectorMXBean gcbean : gcbeans) {
            System.out.println("GC name:" + gcbean.getName());


            NotificationEmitter emitter = (NotificationEmitter) gcbean;
            NotificationListener listener = (notification, handback) -> {

                if (notification.getType().equals(GarbageCollectionNotificationInfo.GARBAGE_COLLECTION_NOTIFICATION)) {
                    GarbageCollectionNotificationInfo info = GarbageCollectionNotificationInfo.from((CompositeData) notification.getUserData());

                    long duration = info.getGcInfo().getDuration();
                    String gctype = info.getGcAction();
                    String name = info.getGcName();
                    String fullName = gctype + ":" + name;

                    if (infoMap.get(fullName) == null) {
                        infoMap.put(fullName, new Info());
                    }
                    infoMap.get(fullName).add(duration);

                }
            };


            emitter.addNotificationListener(listener, null, null);

            registration.add(() -> {
                try {
                    emitter.removeNotificationListener(listener);
                } catch (ListenerNotFoundException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
