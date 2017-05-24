package ru.otus.observer;

import org.junit.Test;

import static org.mockito.Mockito.*;

/**
 * Created by Alexey on 23.05.2017.
 */
public class AtmNotifierTest {
    @Test
    public void notifyTest() {

        Listener listener = spy(new Listener() {
            @Override
            public void handleEvent(Event event) {

            }
        });

        AtmNotifier.getNotifier().submit(Event.SAVE_STATE, listener);
        verify(listener, times(0)).handleEvent(Event.SAVE_STATE);

        AtmNotifier.getNotifier().notify(Event.RESTORE_STATE);
        verify(listener, times(0)).handleEvent(Event.SAVE_STATE);

        AtmNotifier.getNotifier().notify(Event.SAVE_STATE);
        verify(listener, times(1)).handleEvent(Event.SAVE_STATE);

        AtmNotifier.getNotifier().submit(Event.SAVE_STATE, listener);
        AtmNotifier.getNotifier().notify(Event.SAVE_STATE);
        verify(listener, times(3)).handleEvent(Event.SAVE_STATE);

        AtmNotifier.getNotifier().submit(Event.RESTORE_STATE, listener);
        AtmNotifier.getNotifier().notify(Event.RESTORE_STATE);
        verify(listener, times(3)).handleEvent(Event.SAVE_STATE);
        verify(listener, times(1)).handleEvent(Event.RESTORE_STATE);

    }

}


