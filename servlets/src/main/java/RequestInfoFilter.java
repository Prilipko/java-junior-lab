import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class RequestInfoFilter implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        String method = req.getMethod();
        String queryString = req.getQueryString();
        String protocol = req.getProtocol();
        System.out.println(">> RequestInfoFilter: ");
        System.out.println(">>   method: " + method);
        System.out.println(">>   queryString: " + queryString);
        System.out.println(">>   protocol: " + protocol);
        System.out.println();
        filterChain.doFilter(req,servletResponse);
    }

    public void destroy() {

    }
}
