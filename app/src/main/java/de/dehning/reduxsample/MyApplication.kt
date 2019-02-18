package de.dehning.reduxsample

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen

/**
 * Custom [Application] in order to initialise SDKs.
 */
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        AndroidThreeTen.init(this)
    }
}