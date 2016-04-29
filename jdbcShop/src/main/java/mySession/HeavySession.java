package mySession;

import mySession.event.SessionBindingEvent;
import mySession.listener.SessionAttributeListener;

import java.io.Serializable;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class HeavySession implements ShopSession, Serializable {
    transient long lastWorkTime;
    transient private String id;
    transient private SessionAttributeListener sessionAttributeListener;

    private final Map<String, Object> impl = new ConcurrentHashMap<>();

    public HeavySession(String id, SessionAttributeListener sessionAttributeListener) {
        this.id = id;
        resetTime();
        this.sessionAttributeListener = sessionAttributeListener;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public void setAttribute(String key, Object value) {
        if (impl.containsKey(key)) {
            impl.put(key, value);
            sessionAttributeListener.attributeReplaced(
                    new SessionBindingEvent(this, key, value));
        } else {
            impl.put(key, value);
            sessionAttributeListener.attributeAdded(
                    new SessionBindingEvent(this, key, value));
        }
//        resetTime();
    }

    public void removeAttribute(String key) {
        if (impl.containsKey(key)) {
            Object value = impl.remove(key);
            sessionAttributeListener.attributeRemoved(
                    new SessionBindingEvent(this, key, value));
        }
//        resetTime();
    }

    @Override
    public Object getAttribute(String key) {
//        resetTime();
        return impl.get(key);
    }

    public long getLastWorkTime() {
        return lastWorkTime;
    }

    @Override
    public void resetTime() {
        this.lastWorkTime = System.currentTimeMillis();
    }

    @Override
    public Iterator<String> getAttributeNames() {
//        resetTime();
        return impl.keySet().iterator();
    }

    public void setSessionAttributeListener(SessionAttributeListener sessionAttributeListener) {
        this.sessionAttributeListener = sessionAttributeListener;
    }

    @Override
    public String toString() {
        return "HeavySession{" +
                "id='" + id + '\'' +
                ", lastWorkTime=" + lastWorkTime +
                '}';
    }

}
