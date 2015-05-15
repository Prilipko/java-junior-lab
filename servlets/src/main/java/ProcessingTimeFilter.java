import javax.servlet.*;
import java.io.IOException;

public class ProcessingTimeFilter implements Filter {

    public ProcessingTimeFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
          System.out.println(">> ProcessingTimeFilter - init");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long inTime = System.nanoTime();
        filterChain.doFilter(servletRequest, servletResponse);
        long outTime = System.nanoTime();
        System.out.println(">> ProcessingTimeFilter: dT = " + (outTime - inTime));
        System.out.println();
    }

    public void destroy() {
        System.out.println(">> ProcessingTimeFilter - destroy");
    }
}
