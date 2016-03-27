package spring_di;

/**
 * Created by Worker on 27.03.2016.
 */
public class StandardLookup extends SingletonUser {
    Helper helper;

    public StandardLookup(Helper helper) {
        this.helper = helper;
    }

    @Override
    public Helper getHelper() {
        return helper;
    }
}
