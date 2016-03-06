package mySession;

import mySession.event.SessionBindingEvent;
import mySession.event.SessionEvent;
import mySession.listener.SessionAttributeListener;
import mySession.listener.SessionListener;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractShopSessionRepository implements ShopSessionRepository {
    List<SessionListener> sessionListeners;
    List<SessionAttributeListener> sessionAttributeListeners;

    public AbstractShopSessionRepository() {
        sessionListeners = new ArrayList<>();
        sessionAttributeListeners = new ArrayList<>();
    }

    protected void spreadSessionCreatedEvent(SessionEvent se){
        for (SessionListener sessionListener :sessionListeners) {
            sessionListener.sessionCreated(se);
        }
    }

    protected void spreadSessionDestroyedEvent(SessionEvent se){
        for (SessionListener sessionListener :sessionListeners) {
            sessionListener.sessionDestroyed(se);
        }
    }

    protected void spreadAttributeAddedEvent(SessionBindingEvent sbe){
        for (SessionAttributeListener sessionAttributeListener :sessionAttributeListeners) {
            sessionAttributeListener.attributeAdded(sbe);
        }
    }

    protected void spreadAttributeRemovedEvent(SessionBindingEvent sbe){
        for (SessionAttributeListener sessionAttributeListener :sessionAttributeListeners) {
            sessionAttributeListener.attributeRemoved(sbe);
        }
    }

    protected void spreadAttributeReplacedEvent(SessionBindingEvent sbe){
        for (SessionAttributeListener sessionAttributeListener :sessionAttributeListeners) {
            sessionAttributeListener.attributeReplaced(sbe);
        }
    }

    @Override
    public void addSessionListener(SessionListener sessionListener) {
        sessionListeners.add(sessionListener);
    }

    @Override
    public void removeSessionListener(SessionListener sessionListener) {
        sessionListeners.remove(sessionListener);
    }

    @Override
    public void addSessionAttributeListener(SessionAttributeListener sessionAttributeListener) {
        sessionAttributeListeners.add(sessionAttributeListener);
    }

    @Override
    public void removeSessionAttributeListener(SessionAttributeListener sessionAttributeListener) {
        sessionAttributeListeners.remove(sessionAttributeListener);
    }

    final SessionAttributeListener SESSION_ATTRIBUTE_LISTENER = new StandardSessionAttributeListener();

    private class StandardSessionAttributeListener implements SessionAttributeListener{
        @Override
        public void attributeAdded(SessionBindingEvent event) {
            spreadAttributeAddedEvent(event);
        }

        @Override
        public void attributeRemoved(SessionBindingEvent event) {
            spreadAttributeRemovedEvent(event);
        }

        @Override
        public void attributeReplaced(SessionBindingEvent event) {
            spreadAttributeReplacedEvent(event);
        }
    }

}
