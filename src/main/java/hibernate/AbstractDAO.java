package hibernate;

import configuration.HibernateConfiguration;
import model.PlainTweet;
import org.hibernate.Session;

import java.util.List;

/**
 * Created by mgunes on 17.12.2016.
 */
public class AbstractDAO {
    private Session session;

    protected <T> boolean saveList(List<T> list) {
        session = HibernateConfiguration.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            for(Object pl: list){
                session.saveOrUpdate(pl);
            }

            session.flush();
            session.getTransaction().commit();
            return true;
        } catch (Exception ex) {
            return false;
        } finally {
            session.close();
        }
    }
}