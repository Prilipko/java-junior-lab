package mySession.listener;

import mySession.event.SessionEvent;

import java.util.EventListener;

public interface SessionListener extends EventListener {
    void sessionCreated(SessionEvent se);

    void sessionDestroyed(SessionEvent se);
}
