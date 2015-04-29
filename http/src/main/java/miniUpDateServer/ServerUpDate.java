package miniUpDateServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ServerUpDate {

    public static class Head {
        private static Pattern pContentLength = Pattern.compile("Content-Length:.(\\d*)");
        private static Pattern pURI = Pattern.compile("GET|POST\\s(.*)\\sHTTP");
        private static Pattern pMethod = Pattern.compile("(\\w*)\\s");
        final String head;

        final int contentLength;
        final String uri;
        final String method;


        @Override
        public String toString() {
            return "Head{" +
//                    "head='" + head + '\'' +
                    ", contentLength=" + contentLength +
                    ", uri='" + uri + '\'' +
                    ", method='" + method + '\'' +
                    '}';
        }

        public Head(String head) {
            this.head = head;

            Matcher matcher = pContentLength.matcher(head);
            if (matcher.find()) {
                contentLength = Integer.parseInt(matcher.group(1));
            } else {
                contentLength = 0;
            }

            matcher = pMethod.matcher(head);
            if(matcher.find()){
                method = matcher.group(1);
            }else {
                method = "";
            }
            matcher = pURI.matcher(head);
            if(matcher.find()){
                uri = matcher.group(1);
            }else {
                uri = "";
            }



        }

        public int getContentLength() {
            return contentLength;
        }

        public static Head readHead(InputStream is) throws IOException {
            byte[] buffer = new byte[64 * 1024];
            int i = 0;
            while (i < 20 || !(buffer[i - 1] == '\n' && buffer[i - 2] == '\r' && buffer[i - 3] == '\n' && buffer[i - 4] == '\r')) {

//                String s = new String(buffer, "UTF-8");
//                System.out.println(s);
                buffer[i++] = (byte) is.read();
//                i = is.read(buffer, i, buffer.length - i);
            }
            return new Head(new String(buffer, "UTF-8"));
        }
    }

    public static Map<String, String> getParam(InputStream is, int length) throws IOException {
        byte[] data = new byte[length];
        int readCount = is.read(data);
        if (data.length != readCount) {
            throw new IOException("param");
        }
        String[] paramStrings = new String(data, "UTF-8").split("&");
        Map<String, String> param = new HashMap<>();
        for (String s : paramStrings) {
            String[] keyValue = s.split("=");
            if (keyValue.length > 1) {
                param.put(keyValue[0], keyValue[1]);
            }
        }
        return (param);
    }

    public static void main(String[] args) throws Exception {

        try {
            try (ServerSocket serverSocket = new ServerSocket(8080)) {
                while (true) {
                    try (Socket socket = serverSocket.accept()) {
                        try (InputStream is = socket.getInputStream();
                             OutputStream os = socket.getOutputStream()) {

                            Head head = Head.readHead(is);
                            System.out.println(head);
                            Map param = getParam(is, head.contentLength);


                            String outString = "HTTP/1.1 200 OK\n" +
                                    "\n" +
                                    param;
                            os.write(outString.getBytes("UTF-8"));//"HTTP/1.1 200 OK\n\n"


//                        String s = new String(buffer, "UTF-8");
//                        System.out.println(s);
//
//                                    String outString = "HTTP/1.1 200 OK\n" +
//                                    "\n" +
//                                    "This a test!!!";
//                        String outString = "HTTP/1.1 200 OK\n" +
//                                "Date: Mon, 07 Apr 2003 14:51:19 GMT\n" +
//                                "Server: Apache/1.3.20 (Win32) PHP/4.3.0\n" +
//                                "Last-Modified: Mon, 07 Apr 2003 14:51:00 GMT\n" +
//                                "Accept-Ranges: bytes\n" +
//                                "Content-Length: 3\n" +
//                                "Keep-Alive: timeout=15, max=100\n" +
//                                "Connection: Keep-Alive\n" +
//                                "Content-Type: application/zip\n" +
//                                "Content-Disposition: attachment; filename=test.zip\n" +
//                                "Pragma: no-cache\n" +
//                                "\n" +
//                                "123";
//                        os.write(outString.getBytes("UTF-8"));//"HTTP/1.1 200 OK\n\n"
//
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
