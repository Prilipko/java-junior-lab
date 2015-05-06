package ajax;

import com.sun.net.httpserver.*;

import java.io.*;
import java.net.InetSocketAddress;

/**
 * Created by Alexander on 06.05.2015
 */
public class AjaxServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080), 0);

        server.createContext("/", t -> {

            t.getResponseHeaders().add("Content-Type", "text/plain");
            t.getResponseHeaders().add("Cache-Control", "no-cache");

            String res = "OK";

            byte[] bytesRes = res.getBytes("UTF-8");
            t.sendResponseHeaders(200, bytesRes.length);
            t.getResponseBody().write(bytesRes);
        });

        server.createContext("/phones.json", t -> {

            Headers h = t.getResponseHeaders();
            h.add("Content-Type", "application/json");
            h.add("Content-Disposition", "attachment; filename=\"phones.json\"");

            InputStream is = ClassLoader.getSystemResourceAsStream("ajax/phones.json");
            byte[] byteArray = new byte[is.available()];
            BufferedInputStream bis = new BufferedInputStream(is);
            bis.read(byteArray, 0, byteArray.length);

            // ok, we are ready to send the response.
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            t.sendResponseHeaders(200, byteArray.length);
            OutputStream os = t.getResponseBody();
            os.write(byteArray, 0, byteArray.length);
            os.close();
        });
        server.createContext("/digits", t -> {

            Headers h = t.getResponseHeaders();
            h.add("Content-Type", "text/plain");
            h.add("Cache-Control", "no-cache");

            byte[] byteArray = new byte[1000];

            t.sendResponseHeaders(200, byteArray.length * 10);
            OutputStream os = t.getResponseBody();

            for (int i = 0; i < 10; i++) {
                os.write(byteArray, 0, byteArray.length);
                os.flush();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            os.close();
        });


        server.createContext("/JsonExample.html", t -> htmlHandler(t, "ajax/htmls/JsonExample.html"));
        server.createContext("/JsonExampleAsync.html", t -> htmlHandler(t, "ajax/htmls/JsonExampleAsync.html"));
        server.createContext("/digits.html", t -> htmlHandler(t, "ajax/htmls/digits.html"));
        server.createContext("/JsonTask.html", t -> htmlHandler(t, "ajax/htmls/JsonTask.html"));

        server.start();
    }

    private static void htmlHandler(HttpExchange t, String resource) throws IOException {
        Headers h = t.getResponseHeaders();
        h.add("Content-Type", "text/html");
//            h.add("Content-Disposition", "attachment; filename=\"JsonExample.html\"");

        InputStream is = ClassLoader.getSystemResourceAsStream(resource);
        byte[] byteArray = new byte[is.available()];
        BufferedInputStream bis = new BufferedInputStream(is);
        bis.read(byteArray, 0, byteArray.length);

        // ok, we are ready to send the response.
        t.sendResponseHeaders(200, byteArray.length);
        OutputStream os = t.getResponseBody();
        os.write(byteArray, 0, byteArray.length);
        os.close();

    }
}
