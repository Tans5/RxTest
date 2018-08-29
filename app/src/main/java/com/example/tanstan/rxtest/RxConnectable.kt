package com.example.tanstan.rxtest

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.concurrent.TimeUnit

fun useOfConnectable() {
    val obs = Observable.range(0, 100000).sample(7, TimeUnit.MILLISECONDS).publish()

    obs.subscribe(object : Observer<Int> {
        override fun onComplete() {
            System.out.println("#1 on complete")
        }

        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(t: Int) {
            System.out.println("#1 on next: $t")
        }

        override fun onError(e: Throwable) {

        }

    })

    obs.subscribe(object : Observer<Int> {
        override fun onComplete() {
            System.out.println("#2 on complete")
        }

        override fun onSubscribe(d: Disposable) {

        }

        override fun onNext(t: Int) {
            System.out.println("#2 on next: $t")
        }

        override fun onError(e: Throwable) {

        }

    })

    obs.connect()

}