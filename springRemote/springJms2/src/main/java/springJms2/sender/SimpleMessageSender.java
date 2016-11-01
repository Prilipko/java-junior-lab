package springJms2.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

/**
 * Created by Alexander
 * 31.10.2016
 */

@Component
public class SimpleMessageSender {

    @Autowired
    JmsTemplate jmsTemplate;

    public void send(String text){
        jmsTemplate.setDeliveryDelay(5000);
        jmsTemplate.send(session -> session.createTextMessage(text));
    }
}
