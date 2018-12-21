package pl.wdowski.$3rxjavaexample;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

public class PrintContent {

    private static final Logger LOGGER = LoggerFactory.getLogger(PrintContent.class);


    public static void main(String[] args) throws InterruptedException {
        printArray();
        printJust();
        printList();
        printDirectoryContent();
        intervalExample();
        timerExample();
        errorExample();
        emptyExample();
        neverExample();
        rangeEgzample();
        Thread.sleep(2000L);

        //QUESTION: WHERE IS ONCOMPLETED FROM INTERVAL?
    }

    private static void intervalExample() {
        subscribePrint(
                Observable.interval(500L, TimeUnit.MILLISECONDS),
                "Interval Observable"
        );
    }

    private static void timerExample() {
        subscribePrint(
                Observable.timer(1L, TimeUnit.SECONDS),
                "Timed Interval Observable"
        );
    }

    private static void errorExample() {
        subscribePrint(
                Observable.error(new Exception("Test Error!")),
                "Error Observable"
        );
    }

    private static void emptyExample() {
        subscribePrint(Observable.empty(), "Empty Observable");
    }

    private static void neverExample() {
        subscribePrint(Observable.never(), "Never Observable");
    }

    private static void rangeEgzample() {
        subscribePrint(Observable.range(1, 3), "Range Observable");
    }

    private static void printArray() {
        final Observable<Integer> integerObservable = Observable.fromArray(1, 2, 3, 4);
        integerObservable.map(String::valueOf).subscribe(LOGGER::info);
    }

    private static void printJust() {
        final Observable<Integer> just = Observable.just(1, 2, 3, 4, 5);
        just.subscribe(System.out::println);
    }

    private static void printList() {
        final Observable<Integer> integerObservable = Observable.fromIterable(Arrays.asList(1, 2, 1, 2, 1, 2));
        integerObservable.map(String::valueOf).subscribe(LOGGER::info);
    }

    private static void printDirectoryContent() {
        Path path = Paths.get("C:\\Users\\Mateusz\\RxJava\\src\\main\\resources");
        try (DirectoryStream<Path> dStream = Files.newDirectoryStream(path)) {
            Observable<Path> dirObservable = Observable.fromIterable(dStream);
            dirObservable.map(Path::toString).subscribe(LOGGER::info);
//            dirObservable.subscribe(LOGGER::info); // ERROR WHY? STREAM CAN BE USED ONLY ONCE
        } catch (IOException e) {
            LOGGER.error("Got error: ", e);
        }
    }

    public static <T> Disposable subscribePrint(Observable<T> observable, String name) {
        return observable.subscribe(
                (v) -> System.out.println(name + " : " + v),
                (e) -> {
                    System.err.println("Error from " + name + ":");
                    System.err.println(e.getMessage());
                },
                () -> System.out.println(name + " ended!")
        );
    }
}
