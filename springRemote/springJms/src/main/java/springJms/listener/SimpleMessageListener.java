package springJms.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * Created by Alexander
 * 31.10.2016
 */
public class SimpleMessageListener implements MessageListener {
    private static final Logger log = LoggerFactory.getLogger("SimpleMessageListener");
    @Override
    public void onMessage(Message message) {
        TextMessage textMessage = (TextMessage) message;
        try {
            log.info(textMessage.getText());
        }catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
