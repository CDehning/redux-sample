package de.dehning.reduxsample.redux

import com.jakewharton.rxrelay2.PublishRelay
import io.reactivex.Observable

/**
 * Dispatches actions to the store.
 */
abstract class Dispatcher<State, Action>(
    private val actionCreators: List<ActionCreator<Action>>,
    private val recursiveActionCreators: List<RecursiveActionCreator<Action, State>>
) {

    private val actionRelay = PublishRelay.create<Action>()

    /**
     * Dispatches actions. Requires the current state as an observable, so that [RecursiveActionCreator] react to it.
     */
    fun dispatchActions(recursiveState: Observable<State>): Observable<Action> {
        return Observable.merge(
            recursiveActionCreators.map { it.invoke(recursiveState) } +
                    actionCreators.map { it.invoke() } +
                    actionRelay
        )
    }

    /**
     * Dispatches an action.
     */
    fun dispatch(action: Action) {
        actionRelay.accept(action)
    }

}