package exampleAop.annotation;

import org.springframework.stereotype.Component;

@Component
public class PrivateSystem {
    static ThreadLocal<User> userThreadLocal = new InheritableThreadLocal<>();
    public void login(User user){
        userThreadLocal.set(user);
    }
    public void logout(){
        userThreadLocal.remove();
    }

    public User getUser() {
        return userThreadLocal.get();
    }
}
