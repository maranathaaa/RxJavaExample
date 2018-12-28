package pl.wdowski.$3rxjavaexample;

import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

import static pl.wdowski.$3rxjavaexample.PrintContent.subscribePrint;

class Run {
    public static void main(String[] args) {
        ReactiveSumUsingSubject sum = new ReactiveSumUsingSubject();
        subscribePrint(sum.obsC(), "Sum");
        sum.setA(5);
        sum.setB(4);
    }
}

public class ReactiveSumUsingSubject {

    private BehaviorSubject<Double> a = BehaviorSubject.create();
    private BehaviorSubject<Double> b = BehaviorSubject.create();
    private BehaviorSubject<Double> c = BehaviorSubject.create();

    ReactiveSumUsingSubject() {
        Observable.combineLatest(a, b, (x, y) -> x + y).subscribe(c);
    }

    public double getA() {
        return a.getValue();
    }

    public void setA(double a) {
        this.a.onNext(a);
    }

    public double getB() {
        return b.getValue();
    }

    public void setB(double b) {
        this.b.onNext(b);
    }

    public double getC() {
        return c.getValue();
    }

    public Observable<Double> obsC() {
        return c;
    }
}
