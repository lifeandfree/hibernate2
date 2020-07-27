package ru.innopolis.util;

import org.hibernate.event.spi.PreLoadEvent;
import org.hibernate.event.spi.PreLoadEventListener;
import ru.innopolis.entity.Student;

public class StudentListener implements PreLoadEventListener {
    @Override
    public void onPreLoad(PreLoadEvent event) {
        if (event.getEntity() instanceof Student) {
            System.out.println("Hello, i'm student pre load event listener!");
            System.out.println(event);
        }
    }
}
