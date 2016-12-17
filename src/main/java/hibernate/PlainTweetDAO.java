package hibernate;

import configuration.HibernateConfiguration;
import model.PlainTweet;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ercan on 14.12.2016.
 */
public class PlainTweetDAO extends AbstractDAO {
    private Session session;

    public boolean savePlainTweetList(List<PlainTweet> plainTweets){
        return saveList(plainTweets);
    }

    public List<PlainTweet> getRawTweets() {
        List<PlainTweet> plainTweets;
        session = HibernateConfiguration.getSessionFactory().openSession();

        try {
            session.beginTransaction();

            /*String sql = "select p.id, p.tweet, p.screenName, p.userName, p.favoriteCount, p.retweetCount, p.createdDate " +
                    " from ParsedTweet AS o, PlainTweet AS p " +
                    " where o.id <> p.id  and p.tweet NOT LIKE 'RT%'";*/

            String sql =
                    " from PlainTweet AS p " ;

            Query query = session.createQuery(sql);
            plainTweets = (List<PlainTweet>) query.list();

            return plainTweets;
        } catch(Exception ex){
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            session.getTransaction().rollback();
            return null;
        } finally {
            session.close();
        }
    }
}
