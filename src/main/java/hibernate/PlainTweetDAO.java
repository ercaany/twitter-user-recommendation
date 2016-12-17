package hibernate;

import configuration.HibernateConfiguration;
import model.PlainTweet;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.postgresql.util.PSQLException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ercan on 14.12.2016.
 */
public class PlainTweetDAO {
    private Session session;

    public boolean savePlainTweetList(List<PlainTweet> plainTweets){
        session = HibernateConfiguration.getSessionFactory().openSession();
        try{
            session.beginTransaction();

            for(PlainTweet pl: plainTweets){
                session.saveOrUpdate(pl);
            }

            session.flush();
            session.getTransaction().commit();

            return true;
        }catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            session.getTransaction().rollback();
            return false;
        }finally {
            session.close();
        }
    }

    public List<PlainTweet> getRawTweets() {
        List<PlainTweet> plainTweets = new ArrayList<PlainTweet>();
        session = HibernateConfiguration.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            String sql = "select p.id, p.tweet " +
                    " from ordered_word_list o, plain_tweet p " +
                    " where o.id <> p.id  and p.tweet NOT LIKE 'RT%'";

            SQLQuery query = session.createSQLQuery(sql);
            query.addEntity(PlainTweet.class);

        } catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
        session.close();
        }

        return plainTweets;
    }
}
