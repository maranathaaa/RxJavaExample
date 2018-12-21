package pl.wdowski.$3rxjavaexample;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;

import java.util.concurrent.TimeUnit;

import static pl.wdowski.$3rxjavaexample.PrintContent.subscribePrint;

public class HotObservable {

    public static void main(String[] args) throws InterruptedException {
        callAfterConnect();
        replyAllNonRecivedData();
        makeObservableFromConnectObservable();
    }

    private static void makeObservableFromConnectObservable() {
        Observable<Long> interval = Observable.interval(100L,
                TimeUnit.MILLISECONDS);


        Observable<Long> refCount = interval.publish().refCount();
        //OR
//        Observable<Long> refCount = interval.share();

        Disposable sub1 = subscribePrint(refCount, "First");
        Disposable sub2 = subscribePrint(refCount, "Second");
        try {
            Thread.sleep(300L);
        } catch (InterruptedException e) {
        }
        sub1.dispose();
        sub2.dispose();
        Disposable sub3 = subscribePrint(refCount, "Third");
        try {
            Thread.sleep(300L);
        } catch (InterruptedException e) {
        }
        sub3.dispose();
    }

    private static void callAfterConnect() {
        Observable<Long> interval = Observable.interval(100L,
                TimeUnit.MILLISECONDS);
        ConnectableObservable<Long> published = interval.publish();
//        Thread.sleep(5000L);
        Disposable sub1 = subscribePrint(published, "First");
        Disposable sub2 = subscribePrint(published, "Second");
        published.connect();
        Disposable sub3 = null;
        try {
            Thread.sleep(500L);
            sub3 = subscribePrint(published, "Third");
            Thread.sleep(500L);
        } catch (InterruptedException e) {
        }
        sub1.dispose();
        sub2.dispose();
        sub3.dispose();
    }

    private static void replyAllNonRecivedData() throws InterruptedException {
        Observable<Long> interval = Observable.interval(100L,
                TimeUnit.MILLISECONDS);
        ConnectableObservable<Long> published = interval.replay();
        published.connect();
        Thread.sleep(5000L);
        Disposable sub1 = subscribePrint(published, "First");
        Disposable sub2 = subscribePrint(published, "Second");
        Disposable sub3 = null;
        try {
            Thread.sleep(500L);
            sub3 = subscribePrint(published, "Third");
            Thread.sleep(500L);
        } catch (InterruptedException e) {
        }
        sub1.dispose();
        sub2.dispose();
        sub3.dispose();
    }
}
