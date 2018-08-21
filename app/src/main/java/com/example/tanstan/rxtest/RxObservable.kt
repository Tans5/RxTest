package com.example.tanstan.rxtest

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.subscribers.DisposableSubscriber
import java.util.concurrent.TimeUnit


fun useOfInterval() {
    Observable.interval(1, TimeUnit.SECONDS)
            .doOnNext {
                System.out.println("$it")
            }
            .subscribe()
}

fun useOfFromCallable() {
    Flowable.fromCallable { "Hello World" }
            .subscribe(object : DisposableSubscriber<String>() {

                override fun onComplete() {

                }


                override fun onNext(t: String) {

                    System.out.println(t)

                }

                override fun onError(e: Throwable) {

                }

            })
}