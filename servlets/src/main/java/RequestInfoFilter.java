import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.security.Principal;
import java.util.Date;
import java.util.Enumeration;


public class RequestInfoFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        System.out.println(">> RequestInfoFilter: ");
        String authType = req.getAuthType();
        System.out.println(">>   authType = " + authType);
        Cookie[] cookies = req.getCookies();
        System.out.println(">>   cookies:");
        for (Cookie cookie : cookies) {
            System.out.println(">>     " + cookie.getName() + " = " + cookie.getValue());
        }
        Enumeration<String> headerNames = req.getHeaderNames();
        System.out.println(">>   headers:");
        while (headerNames.hasMoreElements()) {
            String name = headerNames.nextElement();
            System.out.print(">>     " + name + ": ");
            Enumeration<String> values = req.getHeaders(name);
            while (values.hasMoreElements()) {
                String value = values.nextElement();
                System.out.print(value + ", ");
            }
            System.out.println();
        }
        String method = req.getMethod();
        System.out.println(">>   method = " + method);
        String pathInfo = req.getPathInfo();
        System.out.println(">>   pathInfo = " + pathInfo);
        String pathTranslated = req.getPathTranslated();
        System.out.println(">>   pathTranslated = " + pathTranslated);
        String contextPath = req.getContextPath();
        System.out.println(">>   contextPath = " + contextPath);
        String queryString = req.getQueryString();
        System.out.println(">>   queryString = " + queryString);
        String remoteUser = req.getRemoteUser();
        System.out.println(">>   remoteUser = " + remoteUser);
        Principal userPrincipal = req.getUserPrincipal();
        System.out.println(">>   userPrincipal = " + (userPrincipal != null ? userPrincipal : "null"));
        String requestedSessionId = req.getRequestedSessionId();
        System.out.println(">>   requestedSessionId = " + requestedSessionId);
        String requestURI = req.getRequestURI();
        System.out.println(">>   requestURI = " + requestURI);
        StringBuffer requestURL = req.getRequestURL();
        System.out.println(">>   requestURL = " + requestURL);
        String servletPath = req.getServletPath();
        System.out.println(">>   servletPath = " + servletPath);
        HttpSession session = req.getSession(false);
        if (session != null) {
            System.out.println(">>   session:");
            System.out.println(">>     attributes:");
            Enumeration<String> attributeNames = session.getAttributeNames();
            while (attributeNames.hasMoreElements()) {
                String name = attributeNames.nextElement();
                System.out.println(">>       " + name + " = " + session.getAttribute(name));
            }
            System.out.println(">>     creationTime = " +new Date(session.getCreationTime()));
            System.out.println(">>     lastAccessedTime = " + new Date(session.getLastAccessedTime()));
            System.out.println(">>     maxInactiveInterval = " + session.getMaxInactiveInterval());
            System.out.println(">>     servletContext = " + session.getServletContext().toString());
            System.out.println(">>     new = " + session.isNew());
            System.out.println(">>     id = " + session.getId());
        } else {
            System.out.println(">>   session = null");
        }
        System.out.println(">>   requestedSessionIdValid = "+ req.isRequestedSessionIdValid());
        System.out.println(">>   requestedSessionIdFromCookie = "+ req.isRequestedSessionIdFromCookie());
        System.out.println(">>   requestedSessionIdFromURL = "+ req.isRequestedSessionIdFromURL());

//        System.out.println(">>   parts:");
//        for(Part part : req.getParts()){
//            System.out.println(">>     "+part.getName());
//        }

        String protocol = req.getProtocol();
        System.out.println(">>   protocol = " + protocol);

        System.out.println();
        filterChain.doFilter(req, servletResponse);
    }

    public void destroy() {

    }
}
