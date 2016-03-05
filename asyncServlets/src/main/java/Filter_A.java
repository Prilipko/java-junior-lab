import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter(urlPatterns = "/async", filterName = "Filter_A", asyncSupported = true)
public class Filter_A implements Filter {
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("Init "+getClass().getName());
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("In "+getClass().getName());
        chain.doFilter(request,response);
        System.out.println("Out "+getClass().getName());
    }

    public void destroy() {
        System.out.println("Destroy "+getClass().getName());
    }
}
