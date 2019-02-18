package de.dehning.reduxsample.mvp

import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable

/**
 * A base presenter for [StateView]s. Needs to be bound to the start/stop lifecycle of the view.
 */
abstract class Presenter<T>(
    private val observable: Observable<T>,
    private val scheduler: Scheduler
) {

    private val compositeDisposable = CompositeDisposable()

    /**
     * To be called in Android lifecycle method onStart.
     */
    fun onStart(view: StateView<T>) {
        compositeDisposable.add(observable
            .observeOn(scheduler)
            .subscribe {
                view.updateState(it)
            })
    }

    /**
     * To be called in Android lifecycle method onStop.
     */
    fun onStop() {
        compositeDisposable.clear()
    }
}

