package regExpExample;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Worker on 01.05.2015
 */
public class RegexpSample {
    public static void main(String[] args) {
        String s="POST /item HTTP/1.1\n" +
                "Host: 189.123.255.239\n" +
                "Content-Type: text/plain\n" +
                "Content-Length: 200";
        Pattern pFirstWorld = Pattern.compile("^(\\w+)\\s"); // first group
        Pattern pSecondURI =Pattern.compile("^(\\w+)\\s(\\S+)\\s"); //second group
        Pattern pContentLength =Pattern.compile("Content-Length:\\s(\\d+)"); //first group

        Matcher mFirstWorld = pFirstWorld.matcher(s);
        Matcher mUri = pSecondURI.matcher(s);
        Matcher mContentLength = pContentLength.matcher(s);

        System.out.println(mFirstWorld.find());
        System.out.println(mUri.find());
        System.out.println(mContentLength.find());

        System.out.println(mFirstWorld.group(1));
        System.out.println(mUri.group(2));
        System.out.println(mContentLength.group(1));
    }
}
