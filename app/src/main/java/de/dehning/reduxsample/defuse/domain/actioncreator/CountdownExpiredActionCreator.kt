package de.dehning.reduxsample.defuse.domain.actioncreator

import de.dehning.reduxsample.defuse.domain.MainAction
import de.dehning.reduxsample.defuse.domain.MainState
import de.dehning.reduxsample.redux.RecursiveActionCreator
import io.reactivex.Observable
import io.reactivex.Observable.empty
import io.reactivex.Observable.timer
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.ZonedDateTime
import org.threeten.bp.temporal.ChronoUnit
import java.util.concurrent.TimeUnit

/**
 * [RecursiveActionCreator] that watches the countdown and emits, whenever it expires.
 */
class CountdownExpiredActionCreator(
    private val timerScheduler: Scheduler = Schedulers.computation()
) : RecursiveActionCreator<MainAction, MainState> {

    override fun invoke(recursiveState: Observable<MainState>): Observable<MainAction> {
        return recursiveState.switchMap {
            if (it is MainState.Active) {
                val millisUntilExpired = ChronoUnit.MILLIS.between(ZonedDateTime.now(), it.countDownEnd)
                timer(millisUntilExpired, TimeUnit.MILLISECONDS, timerScheduler)
                    .map { MainAction.CountdownExpired }
            } else {
                empty()
            }
        }
    }
}