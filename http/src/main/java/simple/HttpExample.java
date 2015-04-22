package simple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by Alexander on 22.04.2015.
 * OSI 7 Levels;
 *
 * 7 Application
 * 6 Presentation
 * 5 Session
 * 4 Transport      TCP- ordering, slow, query; UDP - like IP with port
 * 3 Network        IP 0.0.0.0 - 255.255.255.255 in IP V6 16 bytes, time to live(TTL), lose, reordering, doubling
 * 2 Data Link
 * 1 Physical
 *
 * blocking API
 * 1 one byte come
 * -1 - data is over
 * exception - error
 * 0 can not be
 *
 * 100000 sockets
 * 1000 threads !
 *
 *
 */
public class HttpExample {
    public static void main(String[] args) {
        Socket socket = new Socket();
        try {
            ServerSocket serverSocket = new ServerSocket();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
