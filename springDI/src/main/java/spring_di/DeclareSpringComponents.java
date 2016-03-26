package spring_di;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by Worker on 25.03.2016.
 */
public class DeclareSpringComponents {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-xml.xml");
//        ctx.load("classpath:app-context-annotation.xml");
        ctx.refresh();
        MessageProvider messageProvider =
                ctx.getBean("messageProvider", MessageProvider.class);
        System.out.println(messageProvider.getMessage());
    }
}

class DeclareSpringComponentsInject {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
//        ctx.load("classpath:app-context-xml.xml");
        ctx.load("classpath:app-context-annotation.xml");
        ctx.refresh();
        MessageRenderer messageRenderer =
                ctx.getBean("messageRenderer", MessageRenderer.class);
        messageRenderer.render();
    }
}
