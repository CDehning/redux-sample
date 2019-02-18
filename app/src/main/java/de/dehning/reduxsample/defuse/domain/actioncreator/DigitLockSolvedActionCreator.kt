package de.dehning.reduxsample.defuse.domain.actioncreator

import de.dehning.reduxsample.defuse.domain.Config
import de.dehning.reduxsample.defuse.domain.MainAction
import de.dehning.reduxsample.defuse.domain.MainState
import de.dehning.reduxsample.redux.RecursiveActionCreator
import io.reactivex.Observable
import io.reactivex.Observable.empty
import io.reactivex.Observable.just

/**
 * [RecursiveActionCreator] that recognizes that the digit lock is solved.
 */
class DigitLockSolvedActionCreator : RecursiveActionCreator<MainAction, MainState> {

    override fun invoke(recursiveState: Observable<MainState>): Observable<MainAction> {
        return recursiveState.switchMap {
            if (it is MainState.Active && it.digits == Config.SOLUTION) {
                just(MainAction.DigitLockSolved)
            } else {
                empty()
            }
        }
    }
}