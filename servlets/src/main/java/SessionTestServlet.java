import javax.servlet.ServletException;
import javax.servlet.SessionTrackingMode;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet(urlPatterns = "/sessionTest.do",name = "SessionTestServlet")
public class SessionTestServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        AtomicInteger counter = (AtomicInteger) session.getAttribute("counter");
        if (counter == null) {
            counter = new AtomicInteger(1);
            session.setAttribute("counter", counter);
        }
        resp.getWriter().write("You visit this page " + counter.getAndIncrement() + "'s times");
    }
}
