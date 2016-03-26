package spring_di.xml;

import org.springframework.context.support.GenericXmlApplicationContext;

/**
 * Created by Worker on 25.03.2016.
 */
public class ConstructorsConflict {
    ConstructorsConflict(String p){
        System.out.println("Called String constructor: " + p);
    }
    ConstructorsConflict(int p){
        System.out.println("Called int constructor: " + p);
    }

    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:app-context-xml.xml");
        ctx.refresh();
        ConstructorsConflict constructorsConflict =
                ctx.getBean("constructorConflict", ConstructorsConflict.class);
    }
}
