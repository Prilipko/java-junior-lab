package simple;



import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.Date;

/**
 * port 0..1024 reserve
 * localhost:8080
 * all :80
 * 127.0.0.1
 * 127.0.0.2
 * 127.0.0.3
 *
 * Host: one site for many users
 * headers http:
 *
 * Firefox
 * GET / HTTP/1.1
 * Host: localhost:8080
 * User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0
 * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*|* ;q=0.8
 * Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3
 * Accept-Encoding: gzip, deflate
 * Connection: keep-alive
 *
 * GET /favicon.ico HTTP/1.1
 * Host: localhost:8080
 * User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64; rv:37.0) Gecko/20100101 Firefox/37.0
 * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*|*;q=0.8
 * Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.5,en;q=0.3
 * Accept-Encoding: gzip, deflate
 * Connection: keep-alive
 *
 * ... -|-|- ...
 *
 * GoogleChrome
 *
 * GET / HTTP/1.1
 * Host: localhost:8080
 * Connection: keep-alive
 * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*|*;q=0.8
 * User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36
 * Accept-Encoding: gzip, deflate, sdch
 * Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4
 *
 * GET /favicon.ico HTTP/1.1
 * Host: localhost:8080
 * Connection: keep-alive
 * User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/42.0.2311.90 Safari/537.36
 * Accept: *|*
 * Referer: http://localhost:8080/
 * Accept-Encoding: gzip, deflate, sdch
 * Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4
 *
 * Opera
 *
 * GET / HTTP/1.1
 * Host: localhost:8080
 * Connection: keep-alive
 * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*|*;q=0.8
 * User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36 OPR/28.0.1750.51
 * Accept-Encoding: gzip, deflate, lzma, sdch
 * Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4
 *
 * GET /favicon.ico HTTP/1.1
 * Host: localhost:8080
 * Connection: keep-alive
 * Accept: *|*
 * User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/41.0.2272.118 Safari/537.36 OPR/28.0.1750.51
 * Accept-Encoding: gzip, deflate, lzma, sdch
 * Accept-Language: ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4
 *
 * Safari
 *
 * GET / HTTP/1.1
 * Host: localhost:8080
 * User-Agent: Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/534.54.16 (KHTML, like Gecko) Version/5.1.4 Safari/534.54.16
 * Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*|*;q=0.8
 * Accept-Language: ru-RU
 * Accept-Encoding: gzip, deflate
 * Connection: keep-alive
 *
 * GET /favicon.ico HTTP/1.1
 * Host: localhost:8080
 * User-Agent: Mozilla/5.0 (Windows NT 6.2; WOW64) AppleWebKit/534.54.16 (KHTML, like Gecko) Version/5.1.4 Safari/534.54.16
 * Accept: *|*
 * Referer: http://localhost:8080/
 * Accept-Language: ru-RU
 * Accept-Encoding: gzip, deflate
 * Connection: keep-alive
 *
 * Internet Explorer
 *
 * GET / HTTP/1.1
 * Accept: text/html, application/xhtml+xml, *|*
 * Accept-Language: ru,en-US;q=0.7,en;q=0.3
 * User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko
 * Accept-Encoding: gzip, deflate
 * Host: localhost:8080
 * Connection: Keep-Alive
 *
 * GET /favicon.ico HTTP/1.1
 * Accept: *|*
 * Accept-Encoding: gzip, deflate
 * User-Agent: Mozilla/5.0 (Windows NT 6.3; WOW64; Trident/7.0; rv:11.0) like Gecko
 * Host: localhost:8080
 * Connection: Keep-Alive
 *
 *
 */
public class Server10 {
    private static final Charset US_ASCII = Charset.forName("us-ascii");

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        while(true){
            System.err.println("wait for TCP-connection ...");
            // todo: что происходит при accept?
            // todo: опишите попакетно TCP handshake
            Socket socket = serverSocket.accept();
            System.err.println("get one!");
            try (InputStream in = socket.getInputStream();
            OutputStream err = socket.getOutputStream()){
                // READ request
                byte[] request = HttpUtils.readRequestFully(in);
                System.err.println("  - - - - - - - - - - ");
                System.err.print(new String(request, US_ASCII));
                System.err.println("  - - - - - - - - - - ");
                // WRITE response
                byte[] response = new Date().toString().getBytes(US_ASCII);
                err.write(response);
                err.flush();
            } finally {
                socket.close();
            }
            // todo: что происходит при OS.close() IS.close() socket.close()
        }
    }
}
