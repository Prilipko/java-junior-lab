import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.UnavailableException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/condGet.do",name = "CondGet",asyncSupported = true)
public class CondGet extends HttpServlet{

    @Override
    protected long getLastModified(HttpServletRequest req) {
        return (System.currentTimeMillis()/10_000)*10_000;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().write(String.valueOf((System.currentTimeMillis()/10_000)*10_000));
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
//        throw new ServletException();
//        throw new UnavailableException("time to wait",15);
    }

}
