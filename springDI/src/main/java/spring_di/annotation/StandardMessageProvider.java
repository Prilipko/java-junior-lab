package spring_di.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring_di.MessageProvider;

/**
 * Created by Worker on 25.03.2016.
 */
@Service("messageProvider")
public class StandardMessageProvider implements MessageProvider {
    private String message;

    @Autowired
    public StandardMessageProvider(
//            @Value("Variable annotation message")
                                       String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
