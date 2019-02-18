package de.dehning.reduxsample.mvp

/**
 * A view which gets updates through new state objects of type [T].
 */
interface StateView<T> {

    /**
     * Updates the state of the view.
     */
    fun updateState(state: T)
}