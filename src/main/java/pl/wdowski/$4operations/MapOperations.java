package pl.wdowski.$4operations;

import io.reactivex.Observable;

import static pl.wdowski.$3rxjavaexample.PrintContent.subscribePrint;

public class MapOperations {

    public static void main(String[] args) {
        Observable<String> mapped = Observable
                .just(2, 3, 5, 8)
                .map(v -> v * 3)
                .map(v -> (v % 2 == 0) ? "even" : "odd");
        subscribePrint(mapped, "map");
    }
}
