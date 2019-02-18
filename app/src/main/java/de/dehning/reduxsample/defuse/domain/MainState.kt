package de.dehning.reduxsample.defuse.domain

import org.threeten.bp.ZonedDateTime

/**
 * Possible states of the main screen.
 */
sealed class MainState {

    /**
     * The bomb is active and needs to be defused.
     */
    data class Active(
        val digits: List<Int>,
        val countDownEnd: ZonedDateTime
    ) : MainState()

    /**
     * The bomb exploded.
     */
    object Boom : MainState()

    /**
     * Tha bomb has been defused.
     */
    object Defused : MainState()
}