package jsr330;

import javax.inject.Inject;
import javax.inject.Named;

@Named("messageProvider")
public class CustomMessageProvider implements MessageProvider {
    String message = "Default message";

    public CustomMessageProvider() {
    }


    @Inject()
    @Named("message")
    public CustomMessageProvider(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
