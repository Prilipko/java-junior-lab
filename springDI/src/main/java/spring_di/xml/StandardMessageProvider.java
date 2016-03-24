package spring_di.xml;

import spring_di.MessageProvider;

/**
 * Created by Worker on 25.03.2016.
 */
public class StandardMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
