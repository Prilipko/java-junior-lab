package exampleAop.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
public class HiddenMessage {
    @Secure
    public void show(){
        System.out.println("I am here. Hush...");
    }
}
