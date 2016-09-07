/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package config;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author thanhpham
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure("config/hibernate.cfg.xml");
        StandardServiceRegistryBuilder regBuilder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(regBuilder.build());
        return sessionFactory;
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}
