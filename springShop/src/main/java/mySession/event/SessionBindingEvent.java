package mySession.event;


import mySession.ShopSession;

public class SessionBindingEvent extends SessionEvent {
    private String name;
    private Object value;
    public SessionBindingEvent(ShopSession session, String name) {
        super(session);
        this.name = name;
    }


    public SessionBindingEvent(ShopSession session, String name, Object value) {
        super(session);
        this.name = name;
        this.value = value;
    }

    @Override
    public ShopSession getSession () {
        return super.getSession();
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return this.value;
    }
}
