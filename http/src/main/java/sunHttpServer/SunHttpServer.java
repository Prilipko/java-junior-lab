package sunHttpServer;

import com.sun.net.httpserver.*;
import sun.net.httpserver.HttpServerImpl;
import sun.plugin2.liveconnect.JSExceptions;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;
import java.util.concurrent.Executors;

/**
 * Created by Worker on 01.05.2015
 */
public class SunHttpServer {
    public static void main(String[] args) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress("localhost", 8080),0);

//        server.setExecutor(Executors.newFixedThreadPool(5));

        HttpContext context = server.createContext("/", new GlobalHandler());
        context.getFilters().add(new GlobalFilter());

        server.createContext("/setup.exe", new GetFileHandler());

        server.start();
    }

    private static class GlobalFilter extends Filter {
        @Override
        public String description() {
            return "Parses the requested URI for parameters";
        }

        @Override
        public void doFilter(HttpExchange exchange, Chain chain)
                throws IOException {
            parseGetParameters(exchange);
            parsePostParameters(exchange);
            chain.doFilter(exchange);
        }

        private void parseGetParameters(HttpExchange exchange)
                throws UnsupportedEncodingException {

            Map parameters = new HashMap();
            URI requestedUri = exchange.getRequestURI();
            String query = requestedUri.getRawQuery();
            parseQuery(query, parameters);
            exchange.setAttribute("parameters", parameters);
        }

        private void parsePostParameters(HttpExchange exchange)
                throws IOException {

            if ("post".equalsIgnoreCase(exchange.getRequestMethod())) {
                @SuppressWarnings("unchecked")
                Map parameters =
                        (Map) exchange.getAttribute("parameters");
                InputStreamReader isr =
                        new InputStreamReader(exchange.getRequestBody(), "utf-8");
                BufferedReader br = new BufferedReader(isr);
                String query = br.readLine();
                parseQuery(query, parameters);
            }
        }

        @SuppressWarnings("unchecked")
        private void parseQuery(String query, Map parameters)
                throws UnsupportedEncodingException {

            if (query != null) {
                String pairs[] = query.split("[&]");

                for (String pair : pairs) {
                    String param[] = pair.split("[=]");

                    String key = null;
                    String value = null;
                    if (param.length > 0) {
                        System.out.println(param[0]);
                        key = URLDecoder.decode(param[0], "UTF-8");
//                        ,System.getProperty("file.encoding"));
                    }

                    if (param.length > 1) {
                        System.out.println(param[1]);
                        value = URLDecoder.decode(param[1], "UTF-8");
//                        ,System.getProperty("file.encoding"));
                    }

                    if (parameters.containsKey(key)) {
                        Object obj = parameters.get(key);
                        if (obj instanceof List) {
                            List values = (List) obj;
                            values.add(value);
                        } else if (obj instanceof String) {
                            List values = new ArrayList();
                            values.add((String) obj);
                            values.add(value);
                            parameters.put(key, values);
                        }
                    } else {
                        parameters.put(key, value);
                    }
                }
            }
        }
    }

    static class GetFileHandler implements HttpHandler {
        public void handle(HttpExchange t) throws IOException {

            // add the required response header for a PDF file
            System.out.println("SETUP.EXE");
            Headers h = t.getResponseHeaders();
            h.add("Content-Type", "application/exe");
            h.add("Content-Disposition", "attachment; filename=\"fname.ext\"");

            // a PDF (you provide your own!)
            File file = new File ("C:\\Users\\Worker\\Downloads\\jdk-8-windows-x64.exe");
            byte [] byteArray  = new byte [(int)file.length()];
            FileInputStream fis = new FileInputStream(file);
            BufferedInputStream bis = new BufferedInputStream(fis);
            bis.read(byteArray, 0, byteArray.length);

            // ok, we are ready to send the response.
            t.sendResponseHeaders(200, file.length());
            OutputStream os = t.getResponseBody();
            os.write(byteArray,0,byteArray.length);
            os.close();
        }
    }
    private static class GlobalHandler implements HttpHandler {
        @Override
        public void handle(HttpExchange t) throws IOException {
            Headers headers = t.getRequestHeaders();

            System.out.println(headers.entrySet());

            System.out.println(t.getHttpContext());
            System.out.println(t.getRequestURI());
            System.out.println(t.getProtocol());
            System.out.println(t.getRequestMethod());
            String resp = "Hello";
            if (Objects.equals(t.getRequestMethod(), "POST")) {
                System.out.println("POST");
                Map params = (Map) t.getAttribute("parameters");
                resp = params.toString();
                System.out.println(params);
            }

            byte[] bytesToWrite = resp.getBytes("UTF-8");
            t.sendResponseHeaders(200, bytesToWrite.length);
            OutputStream os = t.getResponseBody();
            os.write(bytesToWrite);
        }
    }
//    public static void main(String[] args) throws UnsupportedEncodingException {
//        System.out.println(URLEncoder.encode("Hello =&  _World!","UTF-8"));
//    }

}
