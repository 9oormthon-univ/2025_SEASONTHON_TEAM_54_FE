package org.ssg_tab

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class SsgTabApplication : Application() {
    @Inject
    override fun onCreate() {
        super.onCreate()
    }
}