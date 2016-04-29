package mySession;

import java.util.Iterator;

/**
 * Created by Worker on 05.03.2016.
 */
public interface ShopSession {
    void setAttribute(String key, Object value);

    Object getAttribute(String key);

    Iterator<String> getAttributeNames();

    void resetTime();
}
