package de.dehning.reduxsample.redux

import io.reactivex.Observable

/**
 * Creates Actions that can change the state of something within a store.
 */
typealias ActionCreator<Action> = () -> Observable<Action>