package springJms;

import org.springframework.context.support.GenericXmlApplicationContext;
import springJms.sender.SimpleMessageSender;

/**
 * Created by Alexander
 * 31.10.2016
 */
public class App {
    public static void main(String[] args) {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.load("jms-sender-app-context.xml",
                "jms-listener-app-context.xml");
        context.refresh();
        SimpleMessageSender sender = context.getBean("simpleMessageSender", SimpleMessageSender.class);
        for (int i = 0; i < 10; i++) {
            sender.send("Text message: " + i);
        }
    }
}
