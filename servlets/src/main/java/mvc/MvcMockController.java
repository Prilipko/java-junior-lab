package mvc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/mvcMock.do", name = "MvcMockController")
public class MvcMockController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("requestAttribute", new MockEntityA());
        req.getSession().setAttribute("sessionAttribute", new MockEntityA());
        req.getServletContext().setAttribute("servletContextAttribute", new MockEntityA());

        req.setAttribute("test", "request");
        req.getSession().setAttribute("test", "session");
        req.getServletContext().setAttribute("test", "servletContext");

        req.getRequestDispatcher("mvcMockView.jsp").forward(req, resp);
    }
}
