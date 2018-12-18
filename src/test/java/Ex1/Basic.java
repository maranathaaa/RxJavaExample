package Ex1;


import io.reactivex.Observable;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Basic {

    @Test
    public void basicExample() {
        //given
        List strings = Arrays.asList("1", "2", "3");

        //when and then
        Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) System.out.println(iterator.next());

        Observable<String> stringObservable = Observable.fromIterable(strings);
        stringObservable.subscribe(System.out::println);

        stringObservable.subscribe(System.out::println);
    }

    @Test
    public void basicErrorHandling(){
        
    }




}
