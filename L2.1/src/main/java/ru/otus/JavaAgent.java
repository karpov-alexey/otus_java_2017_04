package ru.otus;

/**
 * Created by Alexey on 08.04.2017.
 */
import java.lang.instrument.Instrumentation;



public class JavaAgent {
    private static Instrumentation instrumentation;
    private static boolean isInitialize = false;

    static public class NonInitializeJavaAgent extends Throwable
    {

    }

    public static void premain(String args, Instrumentation inst) {
        isInitialize = true;
        instrumentation = inst;
    }

    public static long getObjectSize(Object o) throws NonInitializeJavaAgent {
        if (!isInitialize)
            throw new NonInitializeJavaAgent();

        return instrumentation.getObjectSize(o);
    }
}