package ru.sber.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import ru.sber.entities.ParList;

/**
 * @author Vladimir Zhdanov (mailto:constHomeSpb@gmail.com)
 * @since 0.1
 */
public class Service {
    private final SessionFactory sessionFactory;

    public Service() {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml");
        Metadata metadata = new MetadataSources(createServiceRegistry(configuration))
                .addAnnotatedClass(ParList.class)
                .getMetadataBuilder()
                .build();
        sessionFactory = metadata.getSessionFactoryBuilder().build();

    }

    private static StandardServiceRegistry createServiceRegistry(Configuration configuration){
        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        return serviceRegistry;
    }
    public void create(ParList parList) {
        try (Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            session.save(parList);
            session.getTransaction().commit();
        }
    }
}
