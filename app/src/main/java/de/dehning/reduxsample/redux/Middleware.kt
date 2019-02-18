package de.dehning.reduxsample.redux

/**
 * Middleware that acts upon certain actions.
 */
typealias Middleware<Action> = (action: Action) -> Unit