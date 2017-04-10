package ru.otus.factory;

import java.lang.reflect.Constructor;

/**
 * Created by Alexey on 10.04.2017.
 */
public class NewObjectFactory implements ObjectFactory {

    Class<?> type = null;
    Class<?>[] parameters ;
    Object[] values ;

    public NewObjectFactory(Class<?> type, Class[] parameters, Object[] values )
    {
        this.type = type;
        this.parameters = parameters;
        this.values = values;
    }

    @Override
    public Object createObject() throws FactoryException {
        try {
            Constructor cons = type.getConstructor(parameters);
            Object obj = cons.newInstance(values);
            return obj;

        } catch (Exception e) {
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
