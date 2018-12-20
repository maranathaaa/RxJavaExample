package pl.wdowski.$1basic;

import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

public class BasicErrorHandling {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicErrorHandling.class);


    public static void main(String[] args) {
        final List<String> strings = Arrays.asList("A", "B", "C");

        final Observable<String> stringObservable = Observable.fromIterable(strings);

        stringObservable.subscribe(s -> System.out.println(strings.remove(s)), t -> LOGGER.error("OPERATION NOT SUPPORT", t));
        stringObservable.subscribe(s -> System.out.println(strings.remove(s)), t -> LOGGER.error("OPERATION NOT SUPPORT", t), () -> LOGGER.info("END"));
    }

}
