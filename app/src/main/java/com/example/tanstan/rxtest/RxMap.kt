package com.example.tanstan.rxtest

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

fun useOfFlatMap() {
    Observable.fromIterable(listOf(1,2,3,4,5,6))
            .flatMap {
                Observable.just(it)
                        .map {
                            if(it == 3 ) {
                                Thread.sleep(1000)
                            }
                            it
                        }
                        .subscribeOn(Schedulers.newThread())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    System.out.println("on next: $t")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                    System.out.println("on complete")
                }

            })
}

fun useOfConcatMap() {
    Observable.fromIterable(listOf(1,2,3,4,5,6))
            .concatMap {
                Observable.just(it)
                        .map {
                            if(it == 3 ) {
                                Thread.sleep(1000)
                            }
                            it
                        }
                        .subscribeOn(Schedulers.newThread())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(t: Int) {
                    System.out.println("on next: $t")
                }

                override fun onError(e: Throwable) {
                }

                override fun onComplete() {
                    System.out.println("on complete")
                }

            })
}

fun useOfSwitchMap() {
    Observable.fromIterable(listOf(1,2,3,4,5,6))
            .switchMap {
                Observable.just(it)
                        .subscribeOn(Schedulers.newThread())
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    System.out.println("on complete")
                }

                override fun onSubscribe(d: Disposable) {

                }

                override fun onNext(t: Int) {
                    System.out.println("on next: $t")
                }

                override fun onError(e: Throwable) {

                }

            })
}