package spring_di.annotation;

import org.springframework.stereotype.Service;
import spring_di.MessageProvider;

/**
 * Created by Worker on 25.03.2016.
 */
@Service("messageProvider")
public class StandardMessageProvider implements MessageProvider {
    @Override
    public String getMessage() {
        return "Hello World!";
    }
}
