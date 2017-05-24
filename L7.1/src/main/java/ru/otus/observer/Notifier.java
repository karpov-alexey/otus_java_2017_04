package ru.otus.observer;

/**
 * Created by Alexey on 23.05.2017.
 */
public interface Notifier {
    void submit(Event event, Listener listener);
    void unsubmit(Event event, Listener listener);
    void notify(Event event);
}
