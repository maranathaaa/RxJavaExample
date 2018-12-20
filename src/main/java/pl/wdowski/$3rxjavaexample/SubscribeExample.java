package pl.wdowski.$3rxjavaexample;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SubscribeExample {

    public static void main(String[] args) {
        final Observable<Integer> just = Observable.just(1, 2, 3, 4, 5, 6);
        just.subscribe(s -> System.out.println("Sub OnNext " + s));
        just.subscribe(s -> System.out.println("Sub OnNext " + s),
                t -> System.out.println("Sub OnError" + t));
        just.subscribe(s -> System.out.println("Sub OnNext " + s),
                t -> System.out.println("Sub OnError" + t),
                () -> System.out.println("Sub OnCompleted"));
        just.subscribe(s -> System.out.println("Sub OnNext " + s),
                t -> System.out.println("Sub OnError" + t),
                () -> System.out.println("Sub OnCompleted"),
                disposable -> {
                    if (!disposable.isDisposed()) disposable.dispose();
                });
        just.subscribe(new Observer<Integer>() { // BACK TO EXCEL SUM EXAMPLE
            @Override
            public void onSubscribe(Disposable disposable) {

            }

            @Override
            public void onNext(Integer integer) {

            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onComplete() {

            }
        });
        just.subscribeOn(Schedulers.computation()); // different thread
    }
}
