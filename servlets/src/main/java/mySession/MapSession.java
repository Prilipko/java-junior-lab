package mySession;

import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class MapSession implements ShopSession {
    private final Map<String,Object>impl = new ConcurrentHashMap<>();

    public void setAttribute(String key, Object value){
        impl.put(key, value);
    }

    public Object getAttribute(String key){
        return impl.get(key);
    }

    public Iterator<String> getAttributeNames(){
        return impl.keySet().iterator();
    }
}
