package profiles;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Food {
    String name;

    public Food() {
    }

    public Food(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                '}';
    }

    //-Dspring.profiles.active="school"
    //-Dspring.profiles.active="kindergarten"
    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("*-ctx.xml");
        System.out.println(ctx.getBean("provider", FoodProvider.class).getFood());
    }
}
