import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;

/**
 * Created by Alexander on 19.05.2015.
 */
public class MyServletRequestAttributeListener implements ServletRequestAttributeListener {
    void printSrae(ServletRequestAttributeEvent srae){
        System.out.println(srae.getName()+" = "+srae.getValue());
    }

    public void attributeAdded(ServletRequestAttributeEvent srae) {
        System.out.print(">> ServletRequestAttributeListener:attributeAdded: ");
        printSrae(srae);
    }

    public void attributeRemoved(ServletRequestAttributeEvent srae) {
        System.out.print(">> ServletRequestAttributeListener:attributeRemoved: ");
        printSrae(srae);
    }

    public void attributeReplaced(ServletRequestAttributeEvent srae) {
        System.out.print(">> ServletRequestAttributeListener:attributeReplaced: ");
        printSrae(srae);
    }
}
