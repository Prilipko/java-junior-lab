package myHttpClient;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Worker on 01.05.2015
 */
public class MyHttpClient {


    private static String getNewVersionFile(String server,
                                            String id,
                                            String companyName,
                                            String computerName,
                                            String currentVersion) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            byte[] endOfAnswer = "</html>".getBytes("UTF-8");
            HttpPost httpPost = new HttpPost(server);
            List<NameValuePair> nvps = new ArrayList<>();
            nvps.add(new BasicNameValuePair("id", id));
            nvps.add(new BasicNameValuePair("companyName", companyName));
            nvps.add(new BasicNameValuePair("computerName", computerName));
            nvps.add(new BasicNameValuePair("version", currentVersion));
            httpPost.setEntity(new UrlEncodedFormEntity(nvps, "UTF-8"));
            try (CloseableHttpResponse response = httpclient.execute(httpPost)) {
                HttpEntity entity = response.getEntity();
                byte[] content = new byte[1024 * 2];
                byte[] endOfReceived = new byte[0];
                int count = 0;
                do {
                    count += entity.getContent().read(content, count, content.length - count);
                    if (count >= endOfAnswer.length) {
                        endOfReceived = Arrays.copyOfRange(content, count - endOfAnswer.length, count);
                    }
                } while (count < content.length && !Arrays.equals(endOfAnswer, endOfReceived));
                content = Arrays.copyOf(content, count);
                EntityUtils.consume(entity);
                return new String(content, "UTF-8");
            }
        } catch (IOException ignored) {
        }
        return "";
    }

    private static void saveFileFromInternet(String url, String path) {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet(url);
            try (CloseableHttpResponse response = httpclient.execute(httpget)) {
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    BufferedInputStream bis = new BufferedInputStream(entity.getContent());
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(path)));
                    int inByte;
                    while ((inByte = bis.read()) != -1) bos.write(inByte);
                    bos.flush();
                    bis.close();
                    bos.close();
                    EntityUtils.consume(entity);
                }
            }
        } catch (IOException ignored) {
            ignored.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {

        String newFile = getNewVersionFile("http://test5.sax-reeds.com.ua", "123", "company", "computer", "2");
        newFile = newFile.replaceAll("</?html>","");
        System.out.println(newFile);
        System.out.println("Downloading...");

        File temp = File.createTempFile("newVersionGeC", ".exe");
        System.out.println("Temp file : " + temp.getAbsolutePath());

        saveFileFromInternet(newFile, temp.getAbsolutePath());
        System.out.println("Starting...");

        Runtime.getRuntime().exec(temp.getAbsolutePath());
        System.out.println("OK...");

    }


}
