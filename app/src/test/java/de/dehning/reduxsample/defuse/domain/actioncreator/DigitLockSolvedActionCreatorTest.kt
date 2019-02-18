package de.dehning.reduxsample.defuse.domain.actioncreator

import de.dehning.reduxsample.defuse.domain.Config
import de.dehning.reduxsample.defuse.domain.MainAction
import de.dehning.reduxsample.defuse.domain.MainState
import io.reactivex.Observable.just
import org.junit.Test
import org.threeten.bp.ZonedDateTime


class DigitLockSolvedActionCreatorTest {

    @Test
    fun `Lock is solved`() {
        // Given
        val state = MainState.Active(
            Config.SOLUTION,
            ZonedDateTime.now()
        )

        // When
        val testObserver = DigitLockSolvedActionCreator().invoke(just(state)).test()

        // Then
        testObserver.assertValue(MainAction.DigitLockSolved)
    }

    @Test
    fun `Lock not solved yet`() {
        // Given
        val state = MainState.Active(
            Config.INITIAL,
            ZonedDateTime.now()
        )

        // When
        val testObserver = DigitLockSolvedActionCreator().invoke(just(state)).test()

        // Then
        testObserver.assertNoValues()
    }

}