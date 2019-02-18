package de.dehning.reduxsample.defuse.domain.middleware

import android.util.Log
import de.dehning.reduxsample.defuse.domain.MainAction
import de.dehning.reduxsample.redux.Middleware

/**
 * [Middleware] that logs every Action that is being executed.
 */
class LoggingMiddleware : Middleware<MainAction> {
    override fun invoke(action: MainAction) {
        Log.d("LoggingMiddleware", "Performing action $action")
    }
}