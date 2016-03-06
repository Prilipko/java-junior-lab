import mySession.CustomHttpSessionOnServerRepository;
import mySession.ShopSession;
import mySession.ShopSessionRepository;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import static likeAShop.ShopConstants.*;

@WebServlet(urlPatterns = "/shopSessionTest.do", name = "ShopSessionTestServlet")
public class ShopSessionTestServlet extends HttpServlet {
    public static final String PAGE_ERROR = "error.jsp";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = req.getServletContext();
        ShopSessionRepository repository =
                (ShopSessionRepository) sc.getAttribute(SESSION_PROVIDER);
        if (repository == null) {
            resp.sendError(500, "ShopSessionRepository is null");
            return;
        }
        ShopSession session = repository.getSession(req);
        AtomicInteger counter = (AtomicInteger) session.getAttribute("counter");
        if (counter == null) {
            counter = new AtomicInteger(1);
            session.setAttribute("counter", counter);
        }
        resp.getWriter().write("You visit this page " + counter.getAndIncrement() + "'s times");
        repository.saveSession(resp, session);


    }
}
