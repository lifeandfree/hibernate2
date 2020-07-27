package ru.innopolis.util;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import ru.innopolis.entity.Student;

import java.io.Serializable;

public class StudentInterceptor extends EmptyInterceptor {
    @Override
    public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        if (entity instanceof Student) {
            System.out.println("Hello, i'm student interceptor!");
        }
        return super.onSave(entity, id, state, propertyNames, types);
    }
}
