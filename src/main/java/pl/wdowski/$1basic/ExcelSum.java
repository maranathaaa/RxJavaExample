package pl.wdowski.$1basic;

import io.reactivex.Observable;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.observables.ConnectableObservable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Pattern;

public class ExcelSum {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExcelSum.class);

    public static void main(String... args) {
        LOGGER.info("Write value e.g.: a:4 and b:5: ");
        ConnectableObservable<String> input = from();
        Observable<Double> a = varStream("a", input);
        Observable<Double> b = varStream("b", input);
        new ReactiveSum(a, b);
        input.connect();
    }

    private static ConnectableObservable<String> from() {
        return from(new BufferedReader(new InputStreamReader(System.in)));
    }

    private static ConnectableObservable<String> from(final BufferedReader reader) {
        return Observable.create((ObservableOnSubscribe<String>) observableEmitter -> {
            if (observableEmitter.isDisposed()) {
                return;
            }
            try {
                String line;
                while (!observableEmitter.isDisposed() && (line = reader.readLine()) != null) {
                    if (line.equals("exit")) {
                        break;
                    }
                    observableEmitter.onNext(line);
                }
            } catch (Exception e) {
                observableEmitter.onError(e);
            }
            if (!observableEmitter.isDisposed()) {
                observableEmitter.onComplete();
            }
        }).publish();
    }

    private static Observable<Double> varStream(final String varName, Observable<String> input) {
        final String regex = "(" + varName + ")(:[a-z0-9]+)";
        final Pattern pattern = Pattern.compile(regex);
        return input
                .filter(s -> pattern.matcher(s).matches())
                .map(s -> s.substring(2))
                .map(Double::parseDouble);
    }

    static final class ReactiveSum implements Observer<Double> {
        private double sum;

        ReactiveSum(Observable<Double> aObservable, Observable<Double> bObservable) {
            this.sum = 0;
            Observable<Double> doubleObservable = Observable.combineLatest(aObservable, bObservable, (a, b) -> a + b);
            doubleObservable.subscribe(this);

            //or subscribe with functions not Observer

            doubleObservable.subscribe(sum -> LOGGER.info("Sum: " + sum),
                    error -> LOGGER.error("Got error", error),
                    () -> LOGGER.info("Exit: " + sum));
        }

        @Override
        public void onComplete() {
            LOGGER.info("Exiting last sum was : " + this.sum);
        }

        @Override
        public void onError(Throwable e) {
            LOGGER.error("Got an error!", e);
        }

        @Override
        public void onSubscribe(Disposable disposable) {
        }

        @Override
        public void onNext(Double sum) {
            this.sum = sum;
            LOGGER.info("update : a + b = " + sum);
        }
    }
}
