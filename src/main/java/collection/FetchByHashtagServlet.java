package collection;

import db.hibernate.PlainTweetDAO;
import db.model.PlainTweet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by ercan on 14.12.2016.
 */
@WebServlet(name = "FetchByHashtagServlet", urlPatterns = {"/fetchbyhashtag"})
public class FetchByHashtagServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        HttpSession httpSession = request.getSession();
        httpSession.setAttribute("arff", 0);

        String hashtag = request.getParameter("keyword");
        int count = Integer.parseInt(request.getParameter("count"));
        FetchTweet fetchTweet = new FetchTweet(hashtag, count);
        List<PlainTweet> plainTweetList = fetchTweet.getTweetsByHashtag();
        PlainTweetDAO plainTweetDAO = new PlainTweetDAO();

        if(plainTweetDAO.savePlainTweetList(plainTweetList)){
            httpSession.setAttribute("isSaved", 1);
        } else {
            httpSession.setAttribute("isSaved", 2);
        }

        httpSession.setAttribute("isParsed", 0);
        httpSession.setAttribute("tweetCount", plainTweetList.size());
        response.sendRedirect("index.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
