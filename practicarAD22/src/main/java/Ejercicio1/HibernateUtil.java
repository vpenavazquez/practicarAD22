package Ejercicio1;



import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {


    private static SessionFactory sessionFactory;
    static {
        try{

            sessionFactory = new Configuration().configure().buildSessionFactory();

        }catch (Throwable ex){
            ex.printStackTrace();

        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void shutdown(){
        if(sessionFactory != null){
            sessionFactory.close();
        }
    }
}
