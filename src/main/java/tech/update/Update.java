package tech.update;

import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import hibernateEntities.User;

public class Update {

    public static void main(String[] args) {
        // Load configuration and build session factory
        Configuration cfg = new Configuration();
        cfg.configure("config/hibernate.cfg.xml");  // Ensure the path is correct
        
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();  // Start a transaction
        
        try (Scanner sc = new Scanner(System.in)) {
        	
            System.out.print("Enter id: ");
            int id = sc.nextInt();
            sc.nextLine();  // Consume the leftover newline character
            
            // Retrieve the user by ID
            User user = session.get(User.class, id);
            
            if (user != null) {

                // Take new details from the user
                System.out.print("Enter new Name: ");
                String name = sc.nextLine();
                System.out.print("Enter new Email: ");
                String email = sc.nextLine();
                System.out.print("Enter new Password: ");
                String password = sc.nextLine();
                System.out.print("Enter new City: ");
                String city = sc.nextLine();
                
                // Update the User object with the new values
                user.setName(name);
                user.setEmail(email);
                user.setPassword(password);
                user.setCity(city);
                
                // Save the updated user back to the database
                session.update(user);
                transaction.commit();  // Commit the transaction
                System.out.println("User updated successfully!");
                
            } else {
                System.out.println("User with ID " + id + " not found!");
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
