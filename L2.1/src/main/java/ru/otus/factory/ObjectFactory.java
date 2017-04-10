package ru.otus.factory;

/**
 * Created by Alexey on 10.04.2017.
 */
public interface ObjectFactory {
    Object createObject() throws  FactoryException;
    Class<?> getObjectClass();
}
