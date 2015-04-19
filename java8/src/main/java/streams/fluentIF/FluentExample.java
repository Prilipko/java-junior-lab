package streams.fluentIF;

import static java.util.Arrays.asList;


public class FluentExample {
    public static void main(String[] args) {
        asList("A","BB","CCC").stream()
                .filter(s -> s.length() > 1)
                .map(String::length)
                .forEach(System.out::println);
    }
}
