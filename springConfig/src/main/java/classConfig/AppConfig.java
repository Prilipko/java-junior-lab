package classConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

@Configuration
@ImportResource("classpath:app-context.xml")
@PropertySource("classpath:myMess_ru.properties")
@ComponentScan(basePackages = {"classConfig"})
public class AppConfig {

    @Autowired
    Environment env;

    @Bean
    MessageProvider messageProvider(){
        MessageProvider provider = new MessageProvider();
//        provider.setMessage("Hello, I'm working.");
        provider.setMessage(env.getProperty("hello"));
        return provider;
    }

    @Bean
    MessageRenderer messageRenderer(){
        SoutRenderer renderer = new SoutRenderer();
        renderer.setMessageProvider(messageProvider());
        return renderer;
    }

    public static void main(String[] args) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);
        ctx.getBean("messageRenderer",MessageRenderer.class).render();
    }

}
