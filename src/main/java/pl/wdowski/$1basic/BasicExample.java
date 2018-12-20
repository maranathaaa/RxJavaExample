package pl.wdowski.$1basic;


import io.reactivex.Observable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BasicExample {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicExample.class);

    public static void main(String... args) {
        final List<String> strings = Arrays.asList("1", "2", "3");

        final Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) LOGGER.info(iterator.next());

        final Observable<String> stringObservable = Observable.fromIterable(strings);
        stringObservable.subscribe(s -> LOGGER.info("S1 GOT " + s));
        stringObservable.subscribe(s -> LOGGER.info("S2 GOT " + s));
    }
}
