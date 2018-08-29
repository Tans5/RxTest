package com.example.tanstan.rxtest

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit
import javax.xml.datatype.DatatypeConstants.SECONDS



fun useOfStartWith() {
    Observable.just("Tans", "Yanyan")
            .startWith("Tonya")
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                    System.out.println("on complete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: String) {
                    System.out.println("on next: $t")
                }

                override fun onError(e: Throwable) {

                }

            })
}

fun useOfMerge() {
    val obs1 = Observable.just("Hello")
    val obs2 = Observable.just("World")
    Observable.merge(listOf(obs1, obs2))
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                    System.out.println("on complete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: String) {
                    System.out.println("on next: $t")
                }

                override fun onError(e: Throwable) {

                }

            })
}

fun useOfMergeWith() {
    val sub1 = PublishSubject.create<String>()
    val sub2 = PublishSubject.create<String>()
    sub1.mergeWith(sub2)
            .subscribe {
                System.out.println("on next: $it")
            }
    sub2.onNext("String1")
    sub1.onNext("string2")
    sub2.onNext("string3")
}

fun useOfZip() {
    val obs1 = Observable.fromArray("Hello", "Hello,")
    val obs2 = Observable.fromArray("Tans", "Yanyan", "Tonya")
    Observable.zip(listOf(obs1, obs2)) {
        "${it[0]} ${it[1]}"
    }
            .subscribe(object : Observer<String> {
                override fun onComplete() {
                    System.out.println("on complete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: String) {
                    System.out.println("on next $t")
                }

                override fun onError(e: Throwable) {

                }

            })
}

fun useOfZipWith() {
    val obs1 = Observable.fromArray("Hello", "Hello,")
    val obs2 = Observable.fromArray("Tans", "Yanyan", "Tonya")
    obs1.zipWith(obs2, BiFunction<String, String, String> { a1, a2 -> "$a1 $a2" })
            .subscribe(object : Observer<String> {
        override fun onComplete() {
            System.out.println("on complete")
        }

        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(t: String) {
            System.out.println("on next $t")
        }

        override fun onError(e: Throwable) {

        }

    })
}

fun useOfCombineLatest() {
    val sub1 = BehaviorSubject.createDefault("Hello")
    val sub2 = BehaviorSubject.createDefault("Tans")
    sub2.onNext("Yanyan")
    Observable.combineLatest(listOf(sub1, sub2), Function<Array<Any>, String> {
        "${it[0]}, ${it[1]}"
    }).subscribe(object : Observer<String> {
        override fun onComplete() {
            System.out.println("on complete")
        }

        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(t: String) {
            System.out.println("on next $t")
        }

        override fun onError(e: Throwable) {

        }

    })
    sub1.onNext("Hi~")
    sub2.onNext("Tans")

}

fun useOfWithLatestFrom() {
    val sub1 = BehaviorSubject.createDefault("Hello")
    val sub2 = BehaviorSubject.createDefault("Tans")
    sub2.onNext("Yanyan")
    sub1.withLatestFrom(sub2, BiFunction<String, String, String> { a1, a2 ->
        "$a1, $a2"
    })
            .subscribe(object : Observer<String> {
        override fun onComplete() {
            System.out.println("on complete")
        }

        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(t: String) {
            System.out.println("on next $t")
        }

        override fun onError(e: Throwable) {

        }

    })
    sub1.onNext("Hi~")
    sub2.onNext("Tans")
}

fun useOfSwitchOnNext() {
    val timeIntervals = Observable.interval(10, TimeUnit.SECONDS)
            .map { ticks ->
                Observable.interval(2, TimeUnit.SECONDS)
                        .map { innerInterval -> "outer: $ticks - inner: $innerInterval" }
            }
//            .flatMap { it }
//            .subscribe { System.out.println("on next: $it") }
    Observable.switchOnNext(timeIntervals)
            .subscribe { item -> System.out.println(item) }
}

fun useOfJoin() {
    val itemIntervals = Observable.interval(2, TimeUnit.SECONDS)

    Observable.just("just do it")
            .join(itemIntervals,
                    Function<String, Observable<Long>> {
                        Observable.timer(5000, TimeUnit.MILLISECONDS)
                    },
                    Function<Long, Observable<Long>> {
                        Observable.timer(4000, TimeUnit.MILLISECONDS)
                    },
                    BiFunction<String, Long, String> { a1, a2 ->
                        "$a1 $a2"
                    })
            .subscribe { System.out.println(it) }
}