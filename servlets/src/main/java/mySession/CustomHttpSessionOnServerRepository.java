package mySession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Alexander on 05.06.2015.
 * todo: realize old session clearing functionality
 * todo: realize session listener functionality
 * todo: realize product bucket to this session implementation
 */

public class CustomHttpSessionOnServerRepository {
    private final static Map<String, CustomHttpSession> sessions = new ConcurrentHashMap<>();

    public static CustomHttpSession getSession(String sessionID) {
        return getSession(sessionID, true);
    }

    public static CustomHttpSession getSession(String sessionID, boolean canCreate) {
        if (sessions.containsKey(sessionID)) {
            return sessions.get(sessionID);
        } else {
            if (canCreate) {
                CustomHttpSession newSession = new CustomHttpSession();
                sessions.put(sessionID, newSession);
                return newSession;
            } else {
                return null;
            }
        }
    }

}
