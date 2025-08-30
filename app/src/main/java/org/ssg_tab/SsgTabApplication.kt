package org.ssg_tab

import android.app.Application
import com.kakao.sdk.common.KakaoSdk
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import javax.inject.Inject

@HiltAndroidApp
class SsgTabApplication : Application() {
    @Inject
    override fun onCreate() {
        super.onCreate()
        setTimber()
        initKakaoSdk()
    }

    private fun setTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initKakaoSdk() {
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_APP_KEY)
    }
}