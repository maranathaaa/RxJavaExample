package Ex1;


import io.reactivex.Observable;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class BasicExample {

    public static final Logger LOGGER = LoggerFactory.getLogger(BasicExample.class);

    @Test
    public void basicExample() {
        //given
        List<String> strings = Arrays.asList("1", "2", "3");

        //when and then
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) LOGGER.info(iterator.next());

        Observable<String> stringObservable = Observable.fromIterable(strings);
        stringObservable.subscribe(LOGGER::info);
        stringObservable.subscribe(LOGGER::info);
    }

    @Test
    public void basicErrorHandling() throws RuntimeException {

        List<String> strings = Arrays.asList("A", "B", "C");

        Observable<String> stringObservable = Observable.fromIterable(strings);

        stringObservable.subscribe(s -> System.out.println(strings.remove(s)), t -> LOGGER.error("NOT SUPPORT", t));
        stringObservable.subscribe(s -> System.out.println(strings.remove(s)), t -> LOGGER.error("NOT SUPPORT", t), () -> LOGGER.info("END"));
    }

    

}
