package streams.reduce;

import java.util.List;

import static java.util.Arrays.asList;

public class ReduceExample {
    public static void main(String[] args) {
        List<Integer> list = asList(1, 2, 3, 4, 5);
        System.out.println("sum:" + list.stream().reduce(0, (a, b) -> a + b));
        System.out.println("mul:" + list.stream().reduce(1, (a, b) -> a * b));
        System.out.println("max:" + list.stream().reduce(Integer.MIN_VALUE, Math::max));
        System.out.println("min:" + list.stream().reduce(Integer.MAX_VALUE, Math::min));
    }
}
