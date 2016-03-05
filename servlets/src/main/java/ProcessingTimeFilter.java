import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

public class ProcessingTimeFilter implements Filter {
    private static Logger log = LoggerFactory.getLogger(ProcessingTimeFilter.class);

    public ProcessingTimeFilter() {
    }

    public void init(FilterConfig filterConfig) throws ServletException {
          log.info(">> ProcessingTimeFilter - init");
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        long inTime = System.nanoTime();
        filterChain.doFilter(servletRequest, servletResponse);
        long outTime = System.nanoTime();
        log.info(">> ProcessingTimeFilter: dT = " + (outTime - inTime));
    }

    public void destroy() {
        log.info(">> ProcessingTimeFilter - destroy");
    }
}
