package pl.wdowski.$3rxjavaexample;

import io.reactivex.Observable;

import java.util.Arrays;
import java.util.Iterator;

public class CreateObservable {

    public static void main(String[] args) {
        final Observable<Integer> from = ownFromIterableImplementation(Arrays.asList(1, 2, 3, 4));
        from.subscribe(System.out::println);
    }

    private static <T> Observable<T> ownFromIterableImplementation(final Iterable<T> iterable) {
        return Observable.create(observableEmitter -> {
            final Iterator<T> iterator = iterable.iterator();
            if(observableEmitter.isDisposed()) return;
            iterator.forEachRemaining(observableEmitter::onNext);
            if(!observableEmitter.isDisposed())observableEmitter.onComplete();
        });
    }
}
