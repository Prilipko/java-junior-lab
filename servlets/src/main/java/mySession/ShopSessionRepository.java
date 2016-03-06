package mySession;

import mySession.listener.SessionAttributeListener;
import mySession.listener.SessionListener;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Worker on 05.03.2016.
 */
public interface ShopSessionRepository {
    ShopSession getSession(HttpServletRequest request);
    ShopSession getSession(HttpServletRequest request,boolean canCreate);
    void addSessionListener(SessionListener sessionListener);
    void removeSessionListener(SessionListener sessionListener);
    void addSessionAttributeListener(SessionAttributeListener sessionAttributeListener);
    void removeSessionAttributeListener(SessionAttributeListener sessionAttributeListener);

    void saveSession(HttpServletResponse response, ShopSession session);

    void destroy();
}
