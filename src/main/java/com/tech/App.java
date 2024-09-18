package com.tech;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import hibernateEntities.User;

public class App {
    public static void main(String[] args) {
        User user = new User();
        user.setName("Wasim");
        user.setEmail("wasimkhanktr@gmail.com");
        user.setPassword("123");
        user.setCity("Katihar");

        // Load configuration and build session factory
        Configuration cfg = new Configuration();
        cfg.configure("config/hibernate.cfg.xml");  // Ensure the path is correct
        
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        try {
            session.save(user);  // Save the User object to the database
            transaction.commit();  // Commit the transaction
            System.out.println("User saved successfully!");

        } catch (Exception e) {
            e.printStackTrace();  // Print the stack trace for easier debugging
            transaction.rollback();  // Roll back the transaction in case of error
            System.out.println("Transaction rolled back due to an error.");
        } finally {
            session.close();  // Close the session to release resources
        }
    }
}
