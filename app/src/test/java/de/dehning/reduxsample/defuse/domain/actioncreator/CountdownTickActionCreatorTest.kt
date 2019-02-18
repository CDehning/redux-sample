package de.dehning.reduxsample.defuse.domain.actioncreator

import de.dehning.reduxsample.defuse.domain.MainAction
import de.dehning.reduxsample.defuse.domain.MainState
import de.dehning.reduxsample.defuse.domain.createInitialState
import io.reactivex.Observable.just
import io.reactivex.schedulers.TestScheduler
import org.junit.Test
import java.util.concurrent.TimeUnit


class CountdownTickActionCreatorTest {

    @Test
    fun `Ticks one second after every state emission`() {
        // Given
        val testScheduler = TestScheduler()
        val testObserver = CountdownTickActionCreator(testScheduler).invoke(just(createInitialState())).test()
        testObserver.assertNoValues()

        // When
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        // Then
        testObserver.assertValue(MainAction.Tick)
    }

    @Test
    fun `Does not tick when bomb defused`() {
        // Given
        val testScheduler = TestScheduler()
        val testObserver = CountdownTickActionCreator(testScheduler).invoke(just(MainState.Defused)).test()

        // When
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        // Then
        testObserver.assertNoValues()
    }

    @Test
    fun `Does not tick when bomb exploded`() {
        // Given
        val testScheduler = TestScheduler()
        val testObserver = CountdownTickActionCreator(testScheduler).invoke(just(MainState.Boom)).test()

        // When
        testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)

        // Then
        testObserver.assertNoValues()
    }
}