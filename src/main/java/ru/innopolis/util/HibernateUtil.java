package ru.innopolis.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.BootstrapServiceRegistry;
import org.hibernate.boot.registry.BootstrapServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;
import ru.innopolis.entity.Course;
import ru.innopolis.entity.Student;

import java.sql.Connection;
import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();

                // Hibernate settings equivalent to hibernate.cfg.xml's properties
                Properties settings = new Properties();
                settings.put(Environment.DRIVER, "org.postgresql.Driver");
                settings.put(Environment.URL, "jdbc:postgresql://localhost:5432/test");
                settings.put(Environment.USER, "postgres");
                settings.put(Environment.PASS, "postgres");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.PostgreSQL10Dialect");

                settings.put(Environment.ISOLATION, String.valueOf(Connection.TRANSACTION_READ_COMMITTED));

                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "create");


                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Student.class);
                configuration.addAnnotatedClass(Course.class);

                BootstrapServiceRegistry bootstrapRegistry =
                        new BootstrapServiceRegistryBuilder()
                                .applyIntegrator(new InnoIntegrator())
                                .build();
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder(bootstrapRegistry)
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.setInterceptor(new StudentInterceptor()).buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return sessionFactory;
    }

    public static void shutdown() {
        if (sessionFactory != null) {
            sessionFactory.close();
        }
    }
}
