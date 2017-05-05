package periodic.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class HibernateUtil {

    private static HibernateUtil instance;
    private SessionFactory sessionFactory;
    private static ThreadLocal<Session> sessions = new ThreadLocal<>();



    public static HibernateUtil getInstance(){
        if (instance == null){
            instance = new HibernateUtil();
        }
        return instance;
    }

    public Session getSession(){
        Session session = sessions.get();
        if (session == null){
            session = sessionFactory.openSession();
        }
        return session;
    }

    public void freeSession(Session session){
        if (session != null){
            session.close();
            sessions.remove();
        }

    }

}
