package spring_di.xml;

import spring_di.MessageProvider;

/**
 * Created by Worker on 25.03.2016.
 */
public class StandardMessageProvider implements MessageProvider {
    private String message;

    public StandardMessageProvider(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
