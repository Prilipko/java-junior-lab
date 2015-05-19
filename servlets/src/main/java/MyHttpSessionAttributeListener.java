import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Created by Alexander on 19.05.2015.
 */
public class MyHttpSessionAttributeListener implements HttpSessionAttributeListener {
    public void attributeAdded(HttpSessionBindingEvent event) {
        System.out.println(">> HttpSessionAttributeListener:attributeAdded: " +
                        event.getSession().getId() + ":" +
                        event.getName() + " = " +
                        event.getValue());
    }

    public void attributeRemoved(HttpSessionBindingEvent event) {
        System.out.println(">> HttpSessionAttributeListener:attributeRemoved: " +
                event.getSession().getId() + ":" +
                event.getName() + " = " +
                event.getValue());
    }

    public void attributeReplaced(HttpSessionBindingEvent event) {
        System.out.println(">> HttpSessionAttributeListener:attributeReplaced: " +
                event.getSession().getId() + ":" +
                event.getName() + " = " +
                event.getValue());
    }
}
