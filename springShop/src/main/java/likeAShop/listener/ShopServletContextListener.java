package likeAShop.listener;

import mySession.CustomHttpSessionOnServerRepository;
import mySession.ShopSessionRepository;
import mySession.event.SessionBindingEvent;
import mySession.event.SessionEvent;
import mySession.listener.SessionAttributeListener;
import mySession.listener.SessionListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import static likeAShop.ShopConstants.SESSION_PROVIDER;

@WebListener()
public class ShopServletContextListener implements ServletContextListener {
    private static Logger log = LoggerFactory.getLogger(ShopServletContextListener.class);
    ShopSessionRepository sessions;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("Shop Context Initialized");
        ServletContext sc = sce.getServletContext();


        sessions = new CustomHttpSessionOnServerRepository();
//        sessions = new CustomHttpSessionOnClientRepository();


        sc.setAttribute(SESSION_PROVIDER, sessions);
        sessions.addSessionListener(new SessionListener() {
            @Override
            public void sessionCreated(SessionEvent se) {
                log.info("Session created:" + se.getSession());
            }

            @Override
            public void sessionDestroyed(SessionEvent se) {
                log.info("Session destroyed:" + se.getSession());
            }
        });
        sessions.addSessionAttributeListener(new SessionAttributeListener() {
            @Override
            public void attributeAdded(SessionBindingEvent event) {
                log.info("Attribute added: " + event.getName() + " = " + event.getValue() +
                        " in session: " + event.getSession());
            }

            @Override
            public void attributeRemoved(SessionBindingEvent event) {
                log.info("Attribute removed: " + event.getName() + " = " + event.getValue() +
                        " in session: " + event.getSession());
            }

            @Override
            public void attributeReplaced(SessionBindingEvent event) {
                log.info("Attribute replacer: " + event.getName() + " = " + event.getValue() +
                        " in session: " + event.getSession());
            }
        });

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        sessions.destroy();
    }
}
