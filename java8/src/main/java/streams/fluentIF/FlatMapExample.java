package streams.fluentIF;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;


public class FlatMapExample {
    public static void main(String[] args) {
        List<String> arg1 = Arrays.asList("1","2","3");
        List<String> arg2 = Arrays.asList("A","B");

        mul(arg1,arg2).forEach(System.out::println);
    }

    private static <T>Stream<List<T>> mul(List<T> arg0, List<T> arg1){
        return arg0.stream()
                .flatMap(fst -> arg1.stream().map(snd -> Arrays.asList(fst,snd)));
    }
}
