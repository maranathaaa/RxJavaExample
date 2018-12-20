package pl.wdowski.$2functionalinterface;

import io.reactivex.functions.Function;

public class FunctionExample {

    public static void main(String[] args) throws Exception {

        System.out.println(greet("Hello").apply("world"));

        //GO TO @ExcelSum.class
    }

    private static Function<String, String> greet(String greeting) {
        return (String name) -> greeting + " " + name + "!";
    }
}
