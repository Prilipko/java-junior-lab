package beanFactory;

import javax.annotation.PostConstruct;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FactoryWithoutIF {
    MessageDigest messageDigest;
    String algorithmName = "MD5";

    @PostConstruct
    void init() throws NoSuchAlgorithmException {
        messageDigest = MessageDigest.getInstance(algorithmName);
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public MessageDigest getMessageDigest() {
        return messageDigest;
    }
}
