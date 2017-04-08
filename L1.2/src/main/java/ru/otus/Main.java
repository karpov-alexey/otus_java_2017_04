package ru.otus;

import java.io.IOException;

/**
 * Created by Alexey on 01.04.2017.
 */



class Test
{
    int a;
    int b;
    int c;
    int e;
}


public class Main  {

    public static void main(String[] args) throws IOException, IllegalAccessException, InterruptedException, InstantiationException {
        ObjectSize objectSize = new ObjectSize(String.class, 10_000_000);
        objectSize.calculateByMemoryAllocationInstrument(5);
        objectSize.calculateByJavaAgentInstrument();
    }
}
