package de.dehning.reduxsample.defuse.domain

import de.dehning.reduxsample.defuse.domain.middleware.LoggingMiddleware
import de.dehning.reduxsample.redux.Dispatcher
import de.dehning.reduxsample.redux.Store
import io.reactivex.schedulers.Schedulers

/**
 * Store for the main screen.
 */
class MainStore(dispatcher: Dispatcher<MainState, MainAction>) : Store<MainState, MainAction>(
    dispatcher = dispatcher,
    reducer = MainReducer(),
    initialState = createInitialState(),
    middleware = listOf(
        LoggingMiddleware()
    ),
    computationScheduler = Schedulers.computation()
)