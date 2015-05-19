import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Created by Alexander on 19.05.2015.
 */
public class MyHttpSessionListener implements HttpSessionListener {
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println(">> HttpSessionListener:sessionCreated = "+se.getSession().getId());
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println(">> HttpSessionListener:sessionDestroyed = "+se.getSession().getId());
    }
}
