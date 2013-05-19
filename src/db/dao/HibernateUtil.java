
package db.dao;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            // ------ --- -------------- ---- -----------------
            sessionFactory = new Configuration().configure().buildSessionFactory();
           // Configuration configuration = new Configuration();
            //configuration.configure();
            //ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
            //sessionFactory = configuration.buildSessionFactory(serviceRegistry);
           
  
        } catch (Throwable ex) {
                // Make sure you log the exception, as it might be swallowed
                // ---- ---- --- --- --- ---------- -- -- ----- -- ---------
                System.err.println("Initial SessionFactory creation failed." + ex);
                throw new ExceptionInInitializerError(ex);
        }
    }
                /**
    * Get the configured session factory
    * 
    * @return session factory
    */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
