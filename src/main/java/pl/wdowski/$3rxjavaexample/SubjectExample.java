package pl.wdowski.$3rxjavaexample;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.*;

import java.util.concurrent.TimeUnit;

import static pl.wdowski.$3rxjavaexample.PrintContent.subscribePrint;

public class SubjectExample {
    public static void main(String[] args) throws InterruptedException {

//        createPublishSubject();
//        createReplySubject();
//        createBehaviourSubject();

        createAsyncSubject();
    }

    private static void createAsyncSubject() {
        Observable<Long> interval = Observable.just(1L,2L,3L);
        Subject<Long> publishSubject = AsyncSubject.create(); // buffer capacity eg array list
        interval.subscribe(publishSubject);

//        Thread.sleep(3000L);
        Disposable sub1 = subscribePrint(publishSubject, "First");
        Disposable sub2 = subscribePrint(publishSubject, "Second");
        Disposable sub3 = null;
        try {
            Thread.sleep(300L);
            publishSubject.onNext(555L);//ACCESS TO ONNEXT METHOD
//            publishSubject.onComplete();
            sub3 = subscribePrint(publishSubject, "Third");
            Thread.sleep(500L);
        } catch (InterruptedException e) {
        }
        sub1.dispose();
        sub2.dispose();
        sub3.dispose();
    }

    private static void createBehaviourSubject() throws InterruptedException {
        Observable<Long> interval = Observable.interval(100L,
                TimeUnit.MILLISECONDS);
        Subject<Long> publishSubject = BehaviorSubject.create(); // default capacity is one, return most emitted val
        interval.subscribe(publishSubject);

        Thread.sleep(3000L);
        Disposable sub1 = subscribePrint(publishSubject, "First");
        Disposable sub2 = subscribePrint(publishSubject, "Second");
        Disposable sub3 = null;
        try {
            Thread.sleep(300L);
            publishSubject.onNext(555L);//ACCESS TO ONNEXT METHOD
//            publishSubject.onComplete();
            sub3 = subscribePrint(publishSubject, "Third");
            Thread.sleep(500L);
        } catch (InterruptedException e) {
        }
        sub1.dispose();
        sub2.dispose();
        sub3.dispose();
    }

    private static void createReplySubject() throws InterruptedException {
        Observable<Long> interval = Observable.interval(100L,
                TimeUnit.MILLISECONDS);
        Subject<Long> publishSubject = ReplaySubject.create(1); // buffer capacity eg array list
        interval.subscribe(publishSubject);

        Thread.sleep(3000L);
        Disposable sub1 = subscribePrint(publishSubject, "First");
        Disposable sub2 = subscribePrint(publishSubject, "Second");
        Disposable sub3 = null;
        try {
            Thread.sleep(300L);
            publishSubject.onNext(555L);//ACCESS TO ONNEXT METHOD
//            publishSubject.onComplete();
            sub3 = subscribePrint(publishSubject, "Third");
            Thread.sleep(500L);
        } catch (InterruptedException e) {
        }
        sub1.dispose();
        sub2.dispose();
        sub3.dispose();
    }

    private static void createPublishSubject() {
        Observable<Long> interval = Observable.interval(100L,
                TimeUnit.MILLISECONDS);
        Subject<Long> publishSubject = PublishSubject.create();
        interval.subscribe(publishSubject);
        Disposable sub1 = subscribePrint(publishSubject, "First");
        Disposable sub2 = subscribePrint(publishSubject, "Second");
        Disposable sub3 = null;
        try {
            Thread.sleep(300L);
            publishSubject.onNext(555L);//ACCESS TO ONNEXT METHOD
//            publishSubject.onComplete();
            sub3 = subscribePrint(publishSubject, "Third");
            Thread.sleep(500L);
        } catch (InterruptedException e) {
        }
        sub1.dispose();
        sub2.dispose();
        sub3.dispose();
    }
}
