import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionIdListener;
import java.util.Date;
import java.util.Enumeration;


public class MyHttpSessionIdListener implements HttpSessionIdListener {
    public void sessionIdChanged(HttpSessionEvent event, String oldSessionId) {
        StringBuilder sb = new StringBuilder();
        sb.append(">> HttpSessionIdListener:sessionIdChanged\n");
        sb.append(">>   oldSessionId = " + oldSessionId+'\n');
        HttpSession session = event.getSession();
        if (session != null) {
            sb.append(">>   session:"+'\n');
            sb.append(">>     attributes:"+'\n');
            Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String name = attributeNames.nextElement();
                sb.append(">>       " + name + " = " + session.getAttribute(name)+'\n');
            }
            sb.append(">>     creationTime = " + new Date(session.getCreationTime())+'\n');
            sb.append(">>     lastAccessedTime = " + new Date(session.getLastAccessedTime())+'\n');
            sb.append(">>     maxInactiveInterval = " + session.getMaxInactiveInterval()+'\n');
            sb.append(">>     servletContext = " + session.getServletContext().toString()+'\n');
            sb.append(">>     new = " + session.isNew()+'\n');
            sb.append(">>     id = " + session.getId()+'\n');
        } else {
            sb.append(">>   session = null"+'\n');
        }
        System.out.println(sb.toString());
    }
}
