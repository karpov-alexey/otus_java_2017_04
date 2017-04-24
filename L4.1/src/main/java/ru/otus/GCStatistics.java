package ru.otus;

import com.sun.management.GarbageCollectionNotificationInfo;

import javax.management.ListenerNotFoundException;
import javax.management.NotificationEmitter;
import javax.management.NotificationListener;
import javax.management.openmbean.CompositeData;
import java.lang.management.GarbageCollectorMXBean;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
    private List<Runnable> registration = new ArrayList<>();
    TreeMap<String, Info> infoMap = new TreeMap<>();
    Object lock = new Object();

    public GCStatistics() {
        runGCMonitoring();
    }

    public void printStatistics()
    {
        synchronized(lock) {
            for (Map.Entry<String, Info> entry : infoMap.entrySet()) {
                String name = entry.getKey();
                Info info = entry.getValue();

                long totalTime = 0;
                for (Long duration : info.durations) {
                    totalTime += duration;
                }

                System.out.println(name + ": " + "run count = " + info.durations.size() +
                        "    total time of gc running(ms) = " + totalTime);


                System.out.println("all durations(ms): " + info.durations);

            }
        }
    }


    private void runGCMonitoring() {
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

                    synchronized(lock) {
                        if (infoMap.get(name) == null) {
                            infoMap.put(name, new Info());
                        }
                        infoMap.get(name).add(duration);
                    }

                    //System.out.println(name);

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
