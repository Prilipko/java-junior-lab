import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Alexander on 20.05.2015.
 * cookie
 */
@WebServlet(urlPatterns = "/cookieTest.do", name = "CookiesTestServlet")
public class CookiesTestServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        Cookie cookieFromClient = null;
        for (Cookie cookie : cookies) {
            if ("counter".equals(cookie.getName())) {
                cookieFromClient = cookie;
                break;
            }
        }
        if (cookieFromClient == null) {
            response.addCookie(new Cookie("counter", "1"));
            response.getWriter().write("You visit this page first time");
        } else {
            int visitCount = Integer.valueOf(cookieFromClient.getValue());
            response.addCookie(new Cookie("counter", "" + (visitCount + 1)));
            response.getWriter().write("You visit this page " + (visitCount + 1) + "'s times");
        }
    }
}
