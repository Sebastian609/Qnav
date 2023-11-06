package Session;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.websocket.Session;


public class SessionManager {
    private static final Set<Session> sessions = Collections.synchronizedSet(new HashSet<Session>());

    public static void addSession(Session session) {
        sessions.add(session);
    }

    public static void removeSession(Session session) {
        sessions.remove(session);
    }

    public static Set<Session> getSessions() {
        return sessions;
    }
}

