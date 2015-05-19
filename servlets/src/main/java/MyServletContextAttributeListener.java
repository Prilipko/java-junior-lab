import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;


public class MyServletContextAttributeListener implements ServletContextAttributeListener {

    void printScae(ServletContextAttributeEvent event){
        System.out.println(">>   "+event.getName()+" = "+event.getValue());
    }

    public void attributeAdded(ServletContextAttributeEvent event) {
        System.out.println(">> ServletContextAttributeListener:attributeAdded");
        printScae(event);
    }

    public void attributeRemoved(ServletContextAttributeEvent event) {
        System.out.println(">> ServletContextAttributeListener:attributeRemoved");
        printScae(event);
    }

    public void attributeReplaced(ServletContextAttributeEvent event) {
        System.out.println(">> ServletContextAttributeListener:attributeReplaced");
        printScae(event);
    }
}
