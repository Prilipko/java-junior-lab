package mySession;

import mySession.event.SessionEvent;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Base64;

/**
 * Created by Alexander on 05.06.2015.
 * done: realize old session clearing functionality
 * done: realize session listener functionality
 * done: realize storage session in cookies
 * done: realize product bucket to this session implementation
 */
public class CustomHttpSessionOnClientRepository extends AbstractShopSessionRepository {
    private static final String COOKIES_NAME = "clientSession";
    public static final String CONTEXT_ATTRIBUTE = "CustomHttpSessionOnClientRepositoryAttribute";
    public final static int LIFE_TIME_SECONDS = 10 * 60;

    @Override
    public ShopSession getSession(HttpServletRequest request) {
        return getSession(request, true);
    }

    @Override
    public ShopSession getSession(HttpServletRequest request, boolean canCreate) {
        Cookie[] cookies = request.getCookies();
        String valueString = null;
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (COOKIES_NAME.equals(cookie.getName())) {
                    valueString = cookie.getValue();
                    break;
                }
            }
        }
        if (valueString != null) {
            HeavySession renewSession = stringToSession(valueString);
            renewSession.setSessionAttributeListener(SESSION_ATTRIBUTE_LISTENER);
            renewSession.setId("");
            return renewSession;
        }
        if (canCreate) {
            HeavySession newSession = new HeavySession("", SESSION_ATTRIBUTE_LISTENER);
            spreadSessionCreatedEvent(new SessionEvent(newSession));
            return newSession;
        }
        return null;
    }

    @Override
    public void saveSession(HttpServletResponse response, ShopSession session) {
        Cookie resultCookie = new Cookie(COOKIES_NAME, sessionToString(session));
        resultCookie.setMaxAge(LIFE_TIME_SECONDS);
        response.addCookie(resultCookie);
    }

    @Override
    public void destroy() {
        //NOP
    }

    String sessionToString(ShopSession session) {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(os)) {
            oos.writeObject(session);
            oos.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(os.toByteArray());
    }

    HeavySession stringToSession(String value) {
        byte[] buffer = Base64.getDecoder().decode(value);
        try (ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(buffer))) {
            return (HeavySession) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
