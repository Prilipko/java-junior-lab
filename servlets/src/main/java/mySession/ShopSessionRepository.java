package mySession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Worker on 05.03.2016.
 */
public interface ShopSessionRepository {
    ShopSession getSession(HttpServletRequest request);
    ShopSession getSession(HttpServletRequest request,boolean canCreate);

    void saveSession(HttpServletResponse response, ShopSession session);
}
