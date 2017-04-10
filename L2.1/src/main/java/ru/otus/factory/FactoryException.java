package ru.otus.factory;

/**
 * Created by Alexey on 10.04.2017.
 */
public class FactoryException extends Exception {

    FactoryException() {};

    FactoryException(String message)
    {
        super(message);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
