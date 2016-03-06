package mySession.event;

import mySession.ShopSession;

public class SessionEvent extends java.util.EventObject {
    public SessionEvent(ShopSession source) {
        super(source);
    }
    public ShopSession getSession() {
        return (ShopSession) super.getSource();
    }
}
