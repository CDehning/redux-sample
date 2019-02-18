package de.dehning.reduxsample.defuse.domain.actioncreator

import de.dehning.reduxsample.defuse.domain.MainAction
import de.dehning.reduxsample.defuse.domain.MainState
import de.dehning.reduxsample.redux.RecursiveActionCreator
import io.reactivex.Observable
import io.reactivex.Observable.empty
import io.reactivex.Observable.timer
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

/**
 * [RecursiveActionCreator] that ticks every second - until the bomb is not active anymore.
 */
class CountdownTickActionCreator(
    private val timerScheduler: Scheduler = Schedulers.computation()
) : RecursiveActionCreator<MainAction, MainState> {

    override fun invoke(recursiveState: Observable<MainState>): Observable<MainAction> {
        return recursiveState.switchMap {
            if (it is MainState.Active) {
                timer(1, TimeUnit.SECONDS, timerScheduler)
                    .map { MainAction.Tick }
            } else {
                empty()
            }
        }
    }
}