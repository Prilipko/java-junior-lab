package profiles;

import java.util.Arrays;
import java.util.List;

public class SchoolProvider implements FoodProvider {
    @Override
    public List<Food> getFood() {
        return Arrays.asList(
                new Food("French Fries"),
                new Food("Coke"),
                new Food("Hamburger"));
    }
}
