package de.dehning.reduxsample.defuse.domain.actioncreator

import de.dehning.reduxsample.defuse.domain.Config
import de.dehning.reduxsample.defuse.domain.MainAction
import de.dehning.reduxsample.defuse.domain.MainState
import io.reactivex.Observable
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import org.threeten.bp.ZonedDateTime
import java.util.concurrent.TimeUnit


class CountdownExpiredActionCreatorTest {

    @Test
    fun `Countdown not expired`() {
        // Given
        val state = MainState.Active(
            digits = Config.INITIAL,
            countDownEnd = ZonedDateTime.now().plusSeconds(10L)
        )

        // When
        val testObserver = CountdownExpiredActionCreator().invoke(Observable.just(state)).test()

        // Then
        testObserver.assertNoValues()
    }

    @Test
    fun `Countdown expired`() {
        // Given
        val testScheduler = TestScheduler()
        val state = MainState.Active(
            digits = Config.INITIAL,
            countDownEnd = ZonedDateTime.now().plusSeconds(10L)
        )
        val testObserver = CountdownExpiredActionCreator(testScheduler).invoke(Observable.just(state)).test()

        // When
        testScheduler.advanceTimeBy(10, TimeUnit.SECONDS)

        // Then
        testObserver.assertValue(MainAction.CountdownExpired)
    }
}