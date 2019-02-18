package de.dehning.reduxsample.defuse.domain

import de.dehning.reduxsample.defuse.domain.actioncreator.CountdownExpiredActionCreator
import de.dehning.reduxsample.defuse.domain.actioncreator.CountdownTickActionCreator
import de.dehning.reduxsample.defuse.domain.actioncreator.DigitLockSolvedActionCreator
import de.dehning.reduxsample.redux.Dispatcher

/**
 * Dispatcher for the main screen.
 */
class MainDispatcher : Dispatcher<MainState, MainAction>(
    recursiveActionCreators = listOf(
        DigitLockSolvedActionCreator(),
        CountdownTickActionCreator(),
        CountdownExpiredActionCreator()
    ),
    actionCreators = emptyList()
)