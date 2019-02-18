package de.dehning.reduxsample.redux

import android.util.Log
import com.jakewharton.rxrelay2.BehaviorRelay
import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * Store that ties all the redux components together and makes the current state observable.
 */
abstract class Store<State, Action>(
    private val dispatcher: Dispatcher<State, Action>,
    private val reducer: Reducer<State, Action>,
    private val middleware: List<Middleware<Action>>,
    initialState: State,
    private val computationScheduler: Scheduler
) {

    private var currentState = initialState

    private val recursiveState = BehaviorRelay.create<State>()

    /**
     * Observable state of this store.
     */
    val observable: Observable<State> = Observable.defer {
        dispatcher.dispatchActions(recursiveState)
            .observeOn(computationScheduler)
            .doOnNext { action ->
                middleware.map { it.invoke(action) }
            }
            .map { reducer(currentState, it) }
            .startWith(currentState)
            .doOnNext {
                Log.d("Store", "Emitting new state $it")
                currentState = it
                recursiveState.accept(it)
            }
    }.replay(1).refCount()
}
