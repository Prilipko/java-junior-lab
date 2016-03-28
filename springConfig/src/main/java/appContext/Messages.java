package appContext;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.MessageSource;

import java.util.Locale;

public class Messages implements ApplicationContextAware {
    Locale locale;
    @Autowired
    MessageSource ms;
    ApplicationContext apx;

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public void doAction(String userName) {
        System.out.println(ms.getMessage("hello", null, locale));
        System.out.println(ms.getMessage("myNameIs", new String[]{userName}, locale));
        System.out.println(ms.getMessage("bye", null, locale));
        apx.publishEvent(new MessageEvent(this, "Hello "+userName+" from listener!"));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.apx = applicationContext;
    }
}
