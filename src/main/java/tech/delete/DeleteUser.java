package tech.delete;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import hibernateEntities.User;

public class DeleteUser {

    public static void main(String[] args) {
        // Load configuration and build session factory
        Configuration cfg = new Configuration();
        cfg.configure("config/hibernate.cfg.xml");  // Ensure the path is correct
        
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        
        try {
            // Fetch the user object you want to delete
            int userId = 1;  // The ID of the user to be deleted
            User user = session.get(User.class, userId);  // Load the user with id = 1
            
            if (user != null) {
                // If user exists, remove it from the database
                session.remove(user);  // Use the remove() method to delete the user
                transaction.commit();  // Commit the transaction
                System.out.println("User with ID " + userId + " deleted successfully!");
            } else {
                System.out.println("User with ID " + userId + " not found!");
            }

        } catch (Exception e) {
            transaction.rollback();  // Rollback in case of an error
            e.printStackTrace();
        } finally {
            session.close();  // Close session
            sessionFactory.close();  // Close session factory
        }
    }
}
