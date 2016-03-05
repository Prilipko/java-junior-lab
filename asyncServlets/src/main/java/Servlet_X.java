import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.ExecutorService;

@WebServlet(urlPatterns = "/async", name = "Servlet_X", asyncSupported = true)
public class Servlet_X extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("Servlet " +getClass().toString()+" in");
        final AsyncContext aCtx = req.startAsync();
        Thread asyncThread = new Thread(new Runnable() {
            public void run() {
                System.out.println("AsyncThread in");
                try {
                    Thread.sleep(1000);
                    aCtx.getResponse().getWriter().write("Content");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                aCtx.complete();
                System.out.println("AsyncThread out");
            }
        });
        asyncThread.start();
        System.out.println("Servlet " +getClass().toString()+" out");
    }
}
