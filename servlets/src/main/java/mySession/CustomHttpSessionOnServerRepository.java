package mySession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Alexander on 05.06.2015.
 * todo: realize old session clearing functionality
 * todo: realize session listener functionality
 * todo: realize product bucket to this session implementation
 */

public class CustomHttpSessionOnServerRepository implements ShopSessionRepository {
    public final static String CONTEXT_ATTRIBUTE = "CustomHttpSessionOnServerRepositoryAttribute";
    private final static Map<String, ShopSession> sessions = new ConcurrentHashMap<>();

    private static ShopSession getSession(String sessionID) {
        return getSession(sessionID, true);
    }

    private static ShopSession getSession(String sessionID, boolean canCreate) {
        if (sessions.containsKey(sessionID)) {
            return sessions.get(sessionID);
        } else {
            if (canCreate) {
                ShopSession newSession = new MapSession();
                sessions.put(sessionID, newSession);
                return newSession;
            } else {
                return null;
            }
        }
    }

    @Override
    public ShopSession getSession(HttpServletRequest request, boolean canCreate) {
        HttpSession session = request.getSession(canCreate);
        if (session != null) {
            return getSession(session.getId(), canCreate);
        }
        return null;
    }

    @Override
    public ShopSession getSession(HttpServletRequest request) {
        return getSession(request, true);
    }

    @Override
    public void saveSession(HttpServletResponse response, ShopSession session) {
        //NOP
    }
}
