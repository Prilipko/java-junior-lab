package likeAShop.listener;

import mySession.CustomHttpSessionOnServerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class ShopServletContextListener implements ServletContextListener {
    private static Logger log = LoggerFactory.getLogger(ShopServletContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Shop Context Initialized");
        ServletContext sc = sce.getServletContext();
        sc.setAttribute(CustomHttpSessionOnServerRepository.CONTEXT_ATTRIBUTE,
                new CustomHttpSessionOnServerRepository());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
