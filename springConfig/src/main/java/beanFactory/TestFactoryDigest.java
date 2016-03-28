package beanFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class TestFactoryDigest {
    MessageDigest digest1;
    MessageDigest digest2;

    public void doDigest(String msg) {
        System.out.println("Digest of \"" + msg + '"');
        System.out.println("Algorithm #1: " + digest1.getAlgorithm());
        System.out.println(Arrays.toString(digest1.digest(msg.getBytes())));
        System.out.println("Algorithm #2: " + digest2.getAlgorithm());
        System.out.println(Arrays.toString(digest2.digest(msg.getBytes())));
    }

    public void setDigest1(MessageDigest digest1) {
        this.digest1 = digest1;
    }

    public void setDigest2(MessageDigest digest2) {
        this.digest2 = digest2;
    }
}
