package exampleAop.annotation;

import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("app-context-annot.xml");
        ctx.refresh();

        Divider divider = ctx.getBean("divider", Divider.class);
        try {
            System.out.println(divider.div(4, 2));
            System.out.println(divider.div(0, 2));
            System.out.println(divider.div(4, 0));
        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        PrivateSystem ps = ctx.getBean("privateSystem", PrivateSystem.class);
        HiddenMessage hm = ctx.getBean("hiddenMessage",HiddenMessage.class);
        ps.login(new User("Sasha","qwe"));
        try {
            hm.show();
        }catch (Exception ignore){}
        ps.login(new User("Sasha","123"));
        hm.show();

        User user = ctx.getBean("modifiedUser",User.class);
        IsModified modified = (IsModified) user;

        System.out.println("modified = " + modified.isModified());
        user.setPassword("222");
        System.out.println("modified = " + modified.isModified());
        user.setPassword("333");
        System.out.println("modified = " + modified.isModified());

    }
}
