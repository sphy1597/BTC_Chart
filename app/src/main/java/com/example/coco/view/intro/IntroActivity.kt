package com.example.coco.view.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Observer
import com.example.coco.MainActivity
import com.example.coco.databinding.ActivityIntroBinding
import timber.log.Timber
// splash screen
// https://developer.android.com/develop/ui/views/launch/splash-screen?hl=ko

class IntroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityIntroBinding

    private val viewModel : IntroViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {

        installSplashScreen()

        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Timber.d("onCreate")

        viewModel.checkFirstFlag()

        viewModel.first.observe(this, Observer {
            if(it){
                //  ture >> 접속 기록 있음
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)

            }else {
                // false >> 처음 점속한 유저
                binding.animationView.visibility = View.INVISIBLE
                binding.fragmentContainerView.visibility = View.VISIBLE
            }
        })

    }
}