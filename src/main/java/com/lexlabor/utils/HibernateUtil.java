package com.lexlabor.utils;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import com.lexlabor.entities.Usuario;
import com.lexlabor.entities.LeiTrabalhista;

public class HibernateUtil {
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Usuario.class)
                    .addAnnotatedClass(LeiTrabalhista.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Falha na criação do SessionFactory: " + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown() {
        getSessionFactory().close();
    }
}