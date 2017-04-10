package ru.otus;

import ru.otus.factory.ObjectFactory;

/**
 * Created by Alexey on 10.04.2017.
 */
public class TestFactory implements ObjectFactory
{
    static int i = 0;
    @Override
    public Object createObject()  {
        return  new Test();
    }

    @Override
    public Class<?> getObjectClass() {
        return Test.class;
    }
}