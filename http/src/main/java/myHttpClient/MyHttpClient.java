package myHttpClient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Worker on 01.05.2015
 */
public class MyHttpClient {

    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpclient ;//= HttpClients.createDefault();
//        httpclient.close();
//        HttpGet httpGet = new HttpGet("http://targethost/homepage");
//        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        // The underlying HTTP connection is still held by the response object
        // to allow the response content to be streamed directly from the network socket.
        // In order to ensure correct deallocation of system resources
        // the user MUST call CloseableHttpResponse#close() from a finally clause.
        // Please note that if response content is not fully consumed the underlying
        // connection cannot be safely re-used and will be shut down and discarded
        // by the connection manager.
//        try {
//            System.out.println(response1.getStatusLine());
//            HttpEntity entity1 = response1.getEntity();
//             do something useful with the response body
//             and ensure it is fully consumed
//            EntityUtils.consume(entity1);
//        } finally {
//            response1.close();
//        }

        for(int i= 0;i<2;i++) {
            httpclient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost("http://localhost:8080");
            httpPost.addHeader("Connection","keep-alive");
            List<NameValuePair> nvps = new ArrayList<NameValuePair>();
            nvps.add(new BasicNameValuePair("username", "вг"));
            nvps.add(new BasicNameValuePair("password", "аб"));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            CloseableHttpResponse response2 = httpclient.execute(httpPost);

            try {
                System.out.println(response2.getStatusLine());
                HttpEntity entity2 = response2.getEntity();
                byte[] content = new byte[(int) entity2.getContentLength()];
                entity2.getContent().read(content);
                System.out.println(new String(content, "UTF-8"));
                // do something useful with the response body
                // and ensure it is fully consumed
                EntityUtils.consume(entity2);
            } finally {
                response2.close();
                httpclient.close();
            }
        }

        httpclient = HttpClients.createDefault();

        HttpGet httpget = new HttpGet("http://localhost:8080/setup.exe");
        CloseableHttpResponse response = httpclient.execute(httpget);
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
//                long len = entity.getContentLength();
                System.out.println(entity.getContentLength());
                BufferedInputStream bis = new BufferedInputStream(entity.getContent());
                String filePath = "C:\\Users\\Worker\\Downloads\\autoDownload3.exe";
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(filePath)));
                int inByte;
                while ((inByte = bis.read()) != -1) bos.write(inByte);
                bos.flush();
                bis.close();
                bos.close();
                EntityUtils.consume(entity);
            }
        }finally {
            response.close();
            httpclient.close();
        }

    }


}
