package ru.otus.factory;

/**
 * Created by Alexey on 10.04.2017.
 */
public class ClassFactory implements ObjectFactory {
    Class<?> type;

    public ClassFactory(Class<?> type)
    {
        this.type = type;
    }

    @Override
    public Object createObject() throws FactoryException {
        try {
            return type.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            FactoryException factoryException = new FactoryException();
            factoryException.initCause(e);
            throw factoryException;
        }
    }

    @Override
    public Class<?> getObjectClass() {
        return type;
    }
}
