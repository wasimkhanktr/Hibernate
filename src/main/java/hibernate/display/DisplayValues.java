package hibernate.display;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import hibernateEntities.User;

public class DisplayValues {

    public static void main(String[] args) {
        // Load configuration and build session factory
        Configuration cfg = new Configuration();
        cfg.configure("config/hibernate.cfg.xml");  // Ensure the path is correct
        
        SessionFactory sessionFactory = cfg.buildSessionFactory();
        Session session = sessionFactory.openSession();
//        Transaction transaction = session.beginTransaction();
        
        try {
            int id = 1;
            boolean flag = true;
            
            // Loop through all records, stopping when no user is found
            while (flag) {
                User user = session.get(User.class, id);
                
                if (user != null) {
                    System.out.println("ID: " + user.getId());
                    System.out.println("Name: " + user.getName());
                    System.out.println("Email: " + user.getEmail());
                    System.out.println("Password: " + user.getPassword());
                    System.out.println("City: " + user.getCity());
                    System.out.println("----------");
                    
                    id++;  // Move to the next user ID
                } else {
                    flag = false;  // Stop the loop if no user is found
                }
            }
        } catch (Exception e) {
            e.printStackTrace();  // Print exception stack trace for debugging
            
        }
    }
}
