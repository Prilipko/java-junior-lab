package mySession;

import mySession.event.SessionBindingEvent;
import mySession.event.SessionEvent;
import mySession.listener.SessionAttributeListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by Alexander on 05.06.2015.
 * done: realize old session clearing functionality
 * done: realize session listener functionality
 * done: realize product bucket to this session implementation
 */

public class CustomHttpSessionOnServerRepository extends AbstractShopSessionRepository {
    private static Logger log = LoggerFactory.getLogger(CustomHttpSessionOnServerRepository.class);
    public final static String CONTEXT_ATTRIBUTE = "CustomHttpSessionOnServerRepositoryAttribute";
    public final static int CLEANING_TIME_SECONDS = 5;
    public final static int LIFE_TIME_SECONDS = 10*60;
    private final static Map<String, HeavySession> sessionsById = new ConcurrentHashMap<>();
    private final static ConcurrentSkipListSet<HeavySession> sessionsByTime =
            new ConcurrentSkipListSet<>(new TimeOrderComparator());
    ScheduledExecutorService service;

    private static class TimeOrderComparator implements Comparator<HeavySession> {

        @Override
        public int compare(HeavySession o1, HeavySession o2) {
            return (int) (o1.getLastWorkTime() - o2.getLastWorkTime());
        }
    }

    public CustomHttpSessionOnServerRepository() {
        service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                if(sessionsByTime.isEmpty()) return;
                long currentTime = System.currentTimeMillis();

//                for(HeavySession session: sessionsByTime){
//                    log.info("Time cut "+ (currentTime - session.getLastWorkTime()));
//                }
                while((currentTime - sessionsByTime.first().getLastWorkTime())> LIFE_TIME_SECONDS *1000){
                    HeavySession session = sessionsByTime.first();
                    sessionsByTime.remove(session);
                    removeSession(session.getId());
                    if(sessionsByTime.isEmpty()) return;
                }
            }
        }, 0, CLEANING_TIME_SECONDS, TimeUnit.SECONDS);

    }


    private ShopSession getSession(String sessionID) {
        return getSession(sessionID, true);
    }

    private void reorderForTimes(String sessionID) {
        HeavySession session = sessionsById.get(sessionID);
        sessionsByTime.remove(session);
        session.resetTime();
        sessionsByTime.add(session);
    }

    private ShopSession getSession(String sessionID, boolean canCreate) {
        if (sessionsById.containsKey(sessionID)) {
            reorderForTimes(sessionID);
            return sessionsById.get(sessionID);
        } else {
            if (canCreate) {
                HeavySession newSession = new HeavySession(sessionID, SESSION_ATTRIBUTE_LISTENER);
                sessionsById.put(sessionID, newSession);
                sessionsByTime.add(newSession);
                spreadSessionCreatedEvent(new SessionEvent(newSession));
                return newSession;
            } else {
                return null;
            }
        }
    }

    private ShopSession removeSession(String sessionID) {
        ShopSession result = sessionsById.remove(sessionID);
        if (result != null) {
            spreadSessionDestroyedEvent(new SessionEvent(result));
        }
        return result;
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

    public void destroy(){
        service.shutdown();
    }
}
