package myproject.java;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import Models.Adresse;
import Models.Contact;
import Models.Etudiant;
import Models.Role;

import java.util.Properties;

public class HibernateUtil {
    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration();
                
                Properties settings = new Properties();
                settings.put("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
                settings.put("hibernate.connection.url", "jdbc:mysql://localhost:3306/iit"); // Replace with your DB URL
                settings.put("hibernate.connection.username", "root"); // Replace with your DB username
                settings.put("hibernate.connection.password", "Ayouda123"); // Replace with your DB password
                settings.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                settings.put("hibernate.show_sql", "true");
                settings.put("hibernate.format_sql", "true");
                settings.put("hibernate.hbm2ddl.auto", "update"); // Options: validate | update | create | create-drop
                
                configuration.setProperties(settings);

                configuration.addAnnotatedClass(Etudiant.class);
                configuration.addAnnotatedClass(Adresse.class);
                configuration.addAnnotatedClass(Role.class);
                configuration.addAnnotatedClass(Contact.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
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