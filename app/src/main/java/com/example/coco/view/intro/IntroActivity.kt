package com.example.coco.view.intro

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.coco.R
import timber.log.Timber
// splash screen
// https://developer.android.com/develop/ui/views/launch/splash-screen?hl=ko

class IntroActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        Timber.d("onCreate")
    }
}