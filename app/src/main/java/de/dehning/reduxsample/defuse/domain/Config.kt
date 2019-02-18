package de.dehning.reduxsample.defuse.domain

import org.threeten.bp.ZonedDateTime

/**
 * Configuration for the bomb defusing game.
 */
object Config {
    const val COUNTDOWN_SECONDS = 10L
    val INITIAL = listOf(0, 0, 0, 0)
    val SOLUTION = listOf(1, 1, 0, 4)
}

/**
 * Creates the initial state for the main screen.
 */
fun createInitialState(): MainState {
    return MainState.Active(
        digits = Config.INITIAL,
        countDownEnd = createInitialCountdown()
    )
}

/**
 * Creates the initial countdown.
 */
fun createInitialCountdown(): ZonedDateTime {
    return ZonedDateTime.now().plusSeconds(Config.COUNTDOWN_SECONDS)
}