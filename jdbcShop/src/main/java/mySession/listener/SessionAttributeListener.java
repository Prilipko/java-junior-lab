package mySession.listener;

import mySession.event.SessionBindingEvent;

import java.util.EventListener;

public interface SessionAttributeListener extends EventListener {

    void attributeAdded(SessionBindingEvent event);

    void attributeRemoved(SessionBindingEvent event);

    void attributeReplaced(SessionBindingEvent event);

}
