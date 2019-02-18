package de.dehning.reduxsample.defuse.domain

import de.dehning.reduxsample.redux.Reducer

/**
 * Reducer for the state of the main screen.
 */
class MainReducer : Reducer<MainState, MainAction> {

    override fun invoke(currentState: MainState, action: MainAction): MainState {
        return when (action) {
            MainAction.DigitLockSolved -> MainState.Defused
            is MainAction.DigitLockIncrease -> when (currentState) {
                is MainState.Active -> increaseWithWrap(currentState, action.digit)
                else -> currentState
            }
            MainAction.ResetBomb -> createInitialState()
            MainAction.Tick -> currentState
            MainAction.ResetCountdown -> when (currentState) {
                is MainState.Active -> currentState.copy(countDownEnd = createInitialCountdown())
                else -> currentState
            }
            MainAction.CountdownExpired -> MainState.Boom
        }
    }

    /**
     * Digits values should stay single digit. Makes sure we go back to 0 after we are at 9.
     */
    private fun increaseWithWrap(currentState: MainState.Active, digit: Int): MainState {
        val digits = currentState.digits.toMutableList()
        if (digits[digit] == 9) {
            digits[digit] = 0
        } else {
            digits[digit] = digits[digit] + 1
        }
        return currentState.copy(digits = digits)
    }

}