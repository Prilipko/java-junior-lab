package appContext;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;

public class IncarnationTest {
    public static void main(String[] args) throws IOException {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("app-context.xml");
        ctx.getBean("enMess",Messages.class).doAction("Mark");
        System.out.println();
        ctx.getBean("ruMess",Messages.class).doAction("Ваня");
        System.out.println();

        InputStream is = ctx.getResource("META-INF/LICENSE.txt").getInputStream();
        BufferedReader br = new BufferedReader( new InputStreamReader(is));
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        System.out.println(br.readLine());
        System.out.println(br.readLine());
    }

}
