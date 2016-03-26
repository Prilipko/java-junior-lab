package spring_di.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring_di.MessageProvider;
import spring_di.MessageRenderer;

import javax.annotation.Resource;

/**
 * Created by Worker on 25.03.2016.
 */
@Service("messageRenderer")
public class StandardOutMessageRenderer implements MessageRenderer {
    MessageProvider messageProvider;

    @Override
    public void render() {
        if (messageProvider == null) {
            throw new RuntimeException(
                    "You must set the property messageProvider of class:"
                            + StandardOutMessageRenderer.class.getName());
        }
        System.out.println(messageProvider.getMessage());
    }

    @Override
    @Autowired
//    @Resource(name = "messageProvider")
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}
