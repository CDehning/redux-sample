package de.dehning.reduxsample.defuse.domain

import com.google.common.truth.Truth.assertThat
import org.junit.Assert.fail
import org.junit.Test
import org.threeten.bp.ZonedDateTime

class MainReducerTest {

    @Test
    fun `Digits increase`() {
        // Given
        val state = MainState.Active(
            digits = listOf(0, 1, 2, 3),
            countDownEnd = ZonedDateTime.now()
        )

        // When
        val reduced = MainReducer().invoke(state, MainAction.DigitLockIncrease(0))

        // Then
        assertThat(reduced).isEqualTo(
            state.copy(
                digits = listOf(1, 1, 2, 3)
            )
        )
    }

    @Test
    fun `Digits increase wraps after 9`() {
        // Given
        val state = MainState.Active(
            digits = listOf(0, 1, 2, 9),
            countDownEnd = ZonedDateTime.now()
        )

        // When
        val reduced = MainReducer().invoke(state, MainAction.DigitLockIncrease(3))

        // Then
        assertThat(reduced).isEqualTo(
            state.copy(
                digits = listOf(0, 1, 2, 0)
            )
        )
    }

    @Test
    fun `Reset countdown keeps digits`() {
        // Given
        val state = MainState.Active(
            digits = listOf(0, 1, 2, 3),
            countDownEnd = ZonedDateTime.now()
        )

        // When
        val reduced = MainReducer().invoke(state, MainAction.ResetCountdown)

        // Then
        when (reduced) {
            is MainState.Active -> assertThat(reduced.digits).isEqualTo(state.digits)
            else -> fail()
        }
    }

    @Test
    fun `Reset bomb resets digits`() {
        // Given
        val state = MainState.Active(
            digits = listOf(0, 1, 2, 3),
            countDownEnd = ZonedDateTime.now()
        )

        // When
        val reduced = MainReducer().invoke(state, MainAction.ResetBomb)

        // Then
        when (reduced) {
            is MainState.Active -> assertThat(reduced.digits).isNotEqualTo(state.digits)
            else -> fail()
        }
    }

    @Test
    fun `Ticks don't change state`() {
        // Given
        val state = MainState.Active(
            digits = listOf(0, 1, 2, 3),
            countDownEnd = ZonedDateTime.now()
        )

        // When
        val reduced = MainReducer().invoke(state, MainAction.Tick)

        // Then
        assertThat(reduced).isEqualTo(state)
    }

    @Test
    fun `Defusing the bomb`() {
        // Given
        val state = MainState.Active(
            digits = listOf(0, 1, 2, 3),
            countDownEnd = ZonedDateTime.now()
        )

        // When
        val reduced = MainReducer().invoke(state, MainAction.DigitLockSolved)

        // Then
        assertThat(reduced).isEqualTo(MainState.Defused)
    }

    @Test
    fun `Exploding the bomb`() {
        // Given
        val state = MainState.Active(
            digits = listOf(0, 1, 2, 3),
            countDownEnd = ZonedDateTime.now()
        )

        // When
        val reduced = MainReducer().invoke(state, MainAction.CountdownExpired)

        // Then
        assertThat(reduced).isEqualTo(MainState.Boom)
    }
}