package mySession;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.Iterator;

public class NativeSession implements ShopSession {
    final private HttpSession nativeSession;

    public NativeSession(HttpSession nativeSession) {
        this.nativeSession = nativeSession;
    }

    @Override
    public void setAttribute(String key, Object value) {
        nativeSession.setAttribute(key, value);
    }

    @Override
    public Object getAttribute(String key) {
        return nativeSession.getAttribute(key);
    }

    @Override
    public Iterator<String> getAttributeNames() {
        final Enumeration<String> enumeration = nativeSession.getAttributeNames();
        return new Iterator<String>() {
            @Override
            public boolean hasNext() {
                return enumeration.hasMoreElements();
            }

            @Override
            public String next() {
                return enumeration.nextElement();
            }
        };
    }

    @Override
    public void resetTime() {
    }

    @Override
    public String toString() {
        return "NativeSession{" +
                "nativeSession=" + nativeSession +
                '}';
    }
}
