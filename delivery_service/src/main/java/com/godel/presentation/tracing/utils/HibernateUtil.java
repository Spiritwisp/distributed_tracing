package com.godel.presentation.tracing.utils;

import io.opentracing.contrib.jdbc.TracingDriver;
import org.h2.Driver;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.DriverManager;

public final class HibernateUtil {

  private static final SessionFactory sessionFactory = buildSessionFactory();

  private HibernateUtil() {}

  public static SessionFactory getSessionFactory() {
    return sessionFactory;
  }

  public static void shutdown() {
    // Close caches and connection pools
    getSessionFactory().close();
  }

  private static SessionFactory buildSessionFactory() {
    try {
      // Create the SessionFactory from hibernate.cfg.xml
      Driver h2Driver = Driver.load();
      DriverManager.registerDriver(h2Driver);
//      TracingDriver.ensureRegisteredAsTheFirstDriver();
      return new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    } catch (Throwable ex) {
      System.err.println("Initial SessionFactory creation failed." + ex);
      throw new ExceptionInInitializerError(ex);
    }
  }
}
