package spring_di.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.stereotype.Service;

/**
 * Created by Worker on 25.03.2016.
 */

@Lazy()
@Service("constructorConflict")
public class ConstructorsConflict {
    ConstructorsConflict(String p){
        System.out.println("Called String constructor: " + p);
    }
    @Autowired
    ConstructorsConflict(
//            @Value("30")
            int p){
        System.out.println("Called int constructor: " + p);
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-annotation.xml");
        ctx.refresh();
        ConstructorsConflict constructorsConflict =
                ctx.getBean("constructorConflict", ConstructorsConflict.class);
    }
}
