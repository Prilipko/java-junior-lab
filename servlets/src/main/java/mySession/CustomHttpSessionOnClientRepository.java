package mySession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Alexander on 05.06.2015.
 * todo: realize old session clearing functionality
 * todo: realize session listener functionality
 * todo: realize storage session in cookies
 * todo: realize product bucket to this session implementation
 */
public class CustomHttpSessionOnClientRepository {
    public static MapSession getSession(HttpServletRequest request){
        throw new UnsupportedOperationException();
    }

    public static void saveSession(HttpServletResponse response, MapSession session){
        throw new UnsupportedOperationException();
    }
}
