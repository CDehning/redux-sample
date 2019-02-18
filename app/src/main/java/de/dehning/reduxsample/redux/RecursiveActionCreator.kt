package de.dehning.reduxsample.redux

import io.reactivex.Observable

/**
 * An ActionCreator that has access to the latest state emitted by the store. Handle with care. This is recursive and might end
 * up in infinite loops if used wrong.
 */
typealias RecursiveActionCreator<Action, State> = (recursiveState: Observable<State>) -> Observable<Action>