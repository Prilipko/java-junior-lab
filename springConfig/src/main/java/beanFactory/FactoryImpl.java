package beanFactory;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.support.GenericXmlApplicationContext;

import javax.annotation.PostConstruct;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class FactoryImpl implements FactoryBean<MessageDigest> {
    private String algorithmName = "MD5";
    private MessageDigest messageDigest;

    @PostConstruct
    private void init() throws NoSuchAlgorithmException {
            messageDigest = MessageDigest.getInstance(algorithmName);
            System.out.println("messageDigest is created: "
                    + messageDigest.getAlgorithm());
    }

    @Override
    public MessageDigest getObject() throws Exception {
        System.out.println("messageDigest leaved: "
                + messageDigest.getAlgorithm());
        return messageDigest;
    }

    @Override
    public Class<MessageDigest> getObjectType() {
        return MessageDigest.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setAlgorithmName(String algorithmName) {
        this.algorithmName = algorithmName;
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("factory-impl.xml");
        ctx.refresh();

        //для непосредственного доступа к фабрике перед именем
        //нужно ставить амперсант '&'

        ctx.getBean("testFactoryDigest",TestFactoryDigest.class)
                .doDigest("Hello world");

        ctx.getBean("testNoIfFactory",TestFactoryDigest.class)
                .doDigest("Buy world");
    }
}
