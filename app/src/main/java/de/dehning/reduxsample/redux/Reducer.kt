package de.dehning.reduxsample.redux

/**
 * Reduces the state and an action to a new state.
 */
typealias Reducer<State, Action> = (currentState: State, action: Action) -> State