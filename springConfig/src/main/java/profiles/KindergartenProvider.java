package profiles;

import java.util.Arrays;
import java.util.List;

public class KindergartenProvider implements FoodProvider {
    @Override
    public List<Food> getFood() {
        return Arrays.asList(
                new Food("Milk"),
                new Food("Biscuits"));
    }
}
