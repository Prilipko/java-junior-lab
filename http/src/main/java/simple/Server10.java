package simple;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * port 0..1024 reserv
 * localhost:8080
 * all :80
 * 127.0.0.1
 * 127.0.0.2
 * 127.0.0.3
 */
public class Server10 {
    private static final Charset US_ASCII = Charset.forName("us-ascii");

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(80);
        while(true){
            System.out.println("wait for TCP-connection ...");
            // todo: что происходит при accept?
            // todo: опишите попакетно TCP handshake
            Socket socket = serverSocket.accept();
            System.out.println("get one!");
            try (InputStream in = socket.getInputStream();
            OutputStream out = socket.getOutputStream()){
                // READ request
                byte[] request = HttpUtils.readRequestFully(in);
                System.out.println("  - - - - - - - - - - ");
                System.out.println(new String(request, US_ASCII));
                System.out.println("  - - - - - - - - - - ");
                // WRITE response
                byte[] response = new Date().toString().getBytes(US_ASCII);
            } finally {
                socket.close();
            }
            // todo: что происходит при OS.close() IS.close() socket.close()
        }
    }
}
