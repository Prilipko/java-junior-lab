import javax.servlet.*;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Map;

public class MyServletContextListener implements ServletContextListener {

    void printSce(ServletContextEvent sce){
        StringBuilder sb = new StringBuilder();
        ServletContext sc = sce.getServletContext();
        sb.append(">>   contextPath = " + sc.getContextPath() + '\n');
        sb.append(">>   majorVersion = " + sc.getMajorVersion() + '\n');
        sb.append(">>   minorVersion = " + sc.getMinorVersion() + '\n');
        sb.append(">>   effectiveMajorVersion = " + sc.getEffectiveMajorVersion() + '\n');
        sb.append(">>   effectiveMinorVersion = " + sc.getEffectiveMinorVersion() + '\n');
        sb.append(">>   serverInfo = " + sc.getServerInfo() + '\n');
        sb.append(">>   initParameters:\n");
        Enumeration<String> initParametersNames = sc.getInitParameterNames();
        while (initParametersNames.hasMoreElements()){
            String initParameterName = initParametersNames.nextElement();
            sb.append(">>     "+initParameterName+" = " + sc.getInitParameter(initParameterName) + '\n');
        }
        sb.append(">>   attributes:\n");
        Enumeration<String> attributesNames = sc.getAttributeNames();
        while (attributesNames.hasMoreElements()){
            String attributeName = attributesNames.nextElement();
            sb.append(">>     "+attributeName+" = " + sc.getAttribute(attributeName) + '\n');
        }
        sb.append(">>   servletContextName = " + sc.getServletContextName() + '\n');

        Map<String,? extends ServletRegistration> servletRegistrations = sc.getServletRegistrations();
        if (servletRegistrations != null && servletRegistrations.size() != 0) {
            System.out.println(">>   servletRegistrations:"+ '\n');
            for (Map.Entry<String, ? extends ServletRegistration> entry : servletRegistrations.entrySet()) {
                System.out.println(">>     " + entry.getKey() + ": " + Arrays.toString(entry.getValue().getMappings().toArray())+ '\n');
            }
        }
        Map<String,? extends FilterRegistration> filterRegistrations = sc.getFilterRegistrations();
        if (filterRegistrations != null && filterRegistrations.size() != 0) {
            System.out.println(">>   filterRegistrations:"+ '\n');
            for (Map.Entry<String, ? extends FilterRegistration> entry : filterRegistrations.entrySet()) {
                System.out.println(">>     " + entry.getKey() + ": " +
                        Arrays.toString(entry.getValue().getUrlPatternMappings().toArray())+
                        Arrays.toString(entry.getValue().getServletNameMappings().toArray())+
                        '\n');
            }
        }

        SessionCookieConfig scc = sc.getSessionCookieConfig();
        System.out.println(">>   sessionCookieConfig:" + '\n');

        sb.append(">>     name = " + scc.getName() + '\n');
        sb.append(">>     domain = " + scc.getDomain() + '\n');
        sb.append(">>     path = " + scc.getPath() + '\n');
        sb.append(">>     comment = " + scc.getComment() + '\n');
        sb.append(">>     httpOnly = " + scc.isHttpOnly() + '\n');
        sb.append(">>     secure = " + scc.isSecure() + '\n');
        sb.append(">>     maxAge = " + scc.getMaxAge() + '\n');

        sb.append(">>   effectiveSessionTrackingModes = " + Arrays.toString(sc.getEffectiveSessionTrackingModes().toArray()) + '\n');

        sb.append(">>   virtualServerName = " + sc.getVirtualServerName() + '\n');

        System.out.println(sb.toString());

    }


    public void contextInitialized(ServletContextEvent sce) {
        System.out.println(">> ServletContextListener:contextInitialized\n");
        printSce(sce);
    }

    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println(">> ServletContextListener:Destroyed\n");
        printSce(sce);
    }
}
