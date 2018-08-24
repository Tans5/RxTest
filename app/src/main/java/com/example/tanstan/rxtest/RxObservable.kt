package com.example.tanstan.rxtest

import io.reactivex.*
import io.reactivex.disposables.Disposable
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

fun useOfFromRunnable() {
    Maybe.fromRunnable<Unit> { println("Hello World!!") }
            .subscribe(object : MaybeObserver<Unit> {
                override fun onSuccess(t: Unit) {
                    System.out.println("on success")
                }

                override fun onComplete() {
                    System.out.println("on complete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {
                    System.out.println(e.toString())
                }

            })
}

fun useOfMaybeFromCallable() {
    Maybe.fromCallable { "Hello World!!!" }
            .subscribe(object : MaybeObserver<String> {
                override fun onSuccess(t: String) {
                    System.out.println("on success: $t")
                }

                override fun onComplete() {
                    System.out.println("on complete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }

            })
}

fun useOfFromAction() {
    Maybe.fromAction<Unit> { println("Hello World") }
            .subscribe(object : MaybeObserver<Unit> {
                override fun onSuccess(t: Unit) {
                    System.out.println("on success")
                }

                override fun onComplete() {
                    System.out.println("on complete")
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onError(e: Throwable) {
                }

            })
}

fun useOfSingle() {
    Single.fromCallable { "Hello World" }
            .subscribe(object : SingleObserver<String> {
                override fun onSuccess(t: String) {
                    println("on success: $t")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }

            })
}

fun useOfCompletable() {
    Maybe.fromCallable { "Hello World" }
            .ignoreElement()
            .subscribe(object : CompletableObserver {

                override fun onComplete() {
                    System.out.println("on complete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onError(e: Throwable) {

                }

            })
}