package spring_di.annotation;

import org.springframework.stereotype.Service;
import spring_di.MessageProvider;
import spring_di.MessageRenderer;

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
    public void setMessageProvider(MessageProvider provider) {
        this.messageProvider = provider;
    }

    @Override
    public MessageProvider getMessageProvider() {
        return messageProvider;
    }
}
