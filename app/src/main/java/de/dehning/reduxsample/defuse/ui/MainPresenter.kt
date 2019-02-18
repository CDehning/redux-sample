package de.dehning.reduxsample.defuse.ui

import de.dehning.reduxsample.defuse.domain.MainState
import de.dehning.reduxsample.mvp.Presenter
import io.reactivex.Observable
import io.reactivex.Scheduler

/**
 * [Presenter] for the main screen.
 */
class MainPresenter(
    observable: Observable<MainState>,
    mainThreadScheduler: Scheduler
) : Presenter<MainState>(
    observable = observable,
    scheduler = mainThreadScheduler
)

