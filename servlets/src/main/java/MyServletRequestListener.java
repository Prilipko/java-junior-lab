import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * Created by Alexander on 19.05.2015.
 */
public class MyServletRequestListener implements ServletRequestListener {
    public void requestDestroyed(ServletRequestEvent sre) {
        System.out.println(">> ServletRequestListener:requestDestroyed = "+sre.getServletRequest());
    }

    public void requestInitialized(ServletRequestEvent sre) {
        System.out.println(">> ServletRequestListener:requestInitialized = "+sre.getServletRequest());
    }
}
