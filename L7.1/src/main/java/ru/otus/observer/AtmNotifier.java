package ru.otus.observer;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Created by Alexey on 23.05.2017.
 */

public class AtmNotifier implements Notifier {

    static private AtmNotifier atmNotifier = new AtmNotifier();
    static public AtmNotifier getNotifier() {
        return atmNotifier;
    }

    private Map<Event, List<Listener>> eventMap = new TreeMap<>();

    @Override
    public void submit(Event event, Listener listener) {
        List<Listener> list = eventMap.get(event);
        if (list == null) {
            list = new LinkedList<>();
            eventMap.put(event, list);
        }
        list.add(listener);
    }

    @Override
    public void unsubmit(Event event, Listener listener) {
        System.out.println("Unsupported operation AtmNotifier::unsubmit()");
    }

    @Override
    public void notify(Event event) {
        List<Listener> list = eventMap.get(event);
        if (list != null) {
            for (Listener listener : list) {
                listener.handleEvent(event);
            }
        }
    }
}
