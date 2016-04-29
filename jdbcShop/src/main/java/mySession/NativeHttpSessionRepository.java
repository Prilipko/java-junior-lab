package mySession;

import mySession.event.SessionBindingEvent;
import mySession.event.SessionEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletContext;
import javax.servlet.http.*;

import static likeAShop.ShopConstants.SESSION_PROVIDER;

public class NativeHttpSessionRepository extends AbstractShopSessionRepository {
    private static Logger log = LoggerFactory.getLogger(NativeHttpSessionRepository.class);

    public NativeHttpSessionRepository(ServletContext ctx) {
        ctx.addListener(NativeHttpSessionListener.class);
        ctx.addListener(NativeHttpSessionAttributeListener.class);
    }

    @Override
    public ShopSession getSession(HttpServletRequest request, boolean canCreate) {
        return new NativeSession(request.getSession(canCreate));
    }

    @Override
    public void saveSession(HttpServletResponse response, ShopSession session) {

    }

    @Override
    public void destroy() {

    }

    public static class NativeHttpSessionListener implements HttpSessionListener {
        public void sessionCreated(HttpSessionEvent se) {
            NativeHttpSessionRepository ssr= (NativeHttpSessionRepository) se.getSession().getServletContext().getAttribute(SESSION_PROVIDER);
            ssr.spreadSessionCreatedEvent(new SessionEvent(new NativeSession(se.getSession())));
        }

        public void sessionDestroyed(HttpSessionEvent se) {
            NativeHttpSessionRepository ssr= (NativeHttpSessionRepository) se.getSession().getServletContext().getAttribute(SESSION_PROVIDER);
            ssr.spreadSessionDestroyedEvent(new SessionEvent(new NativeSession(se.getSession())));
        }
    }

    public static class NativeHttpSessionAttributeListener implements HttpSessionAttributeListener {
        public void attributeAdded(HttpSessionBindingEvent event) {
            NativeHttpSessionRepository ssr= (NativeHttpSessionRepository) event.getSession().getServletContext().getAttribute(SESSION_PROVIDER);
            ssr.spreadAttributeAddedEvent(
                    new SessionBindingEvent(
                            new NativeSession(event.getSession()),
                            event.getName(),
                            event.getValue()));
        }

        public void attributeRemoved(HttpSessionBindingEvent event) {
            NativeHttpSessionRepository ssr= (NativeHttpSessionRepository) event.getSession().getServletContext().getAttribute(SESSION_PROVIDER);
            ssr.spreadAttributeRemovedEvent(
                    new SessionBindingEvent(
                            new NativeSession(event.getSession()),
                            event.getName(),
                            event.getValue()));
        }

        public void attributeReplaced(HttpSessionBindingEvent event) {
            NativeHttpSessionRepository ssr= (NativeHttpSessionRepository) event.getSession().getServletContext().getAttribute(SESSION_PROVIDER);
            ssr.spreadAttributeReplacedEvent(
                    new SessionBindingEvent(
                            new NativeSession(event.getSession()),
                            event.getName(),
                            event.getValue()));
        }
    }

}
