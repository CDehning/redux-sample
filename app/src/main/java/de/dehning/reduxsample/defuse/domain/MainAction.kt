package de.dehning.reduxsample.defuse.domain

/**
 * Actions that can change the state of the main screen.
 */
sealed class MainAction {

    /**
     * The lock was solved.
     */
    object DigitLockSolved : MainAction()

    /**
     * The given digit of the lock should be increased.
     */
    data class DigitLockIncrease(val digit: Int) : MainAction()

    /**
     * The whole bomb should be reset.
     */
    object ResetBomb : MainAction()

    /**
     * The countdown timer should be reset.
     */
    object ResetCountdown : MainAction()

    /**
     * The bomb ticks.
     */
    object Tick : MainAction()

    /**
     * The countdown expired.
     */
    object CountdownExpired : MainAction()
}