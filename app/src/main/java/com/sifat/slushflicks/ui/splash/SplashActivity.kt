package com.sifat.slushflicks.ui.splash

import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.REVERSE
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.lifecycle.Observer
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ActivitySplashBinding
import com.sifat.slushflicks.ui.base.BaseActivity
import com.sifat.slushflicks.ui.home.HomeActivity
import com.sifat.slushflicks.ui.splash.viewmodel.SplashViewModel


/**
 * Will follow MVVM for splash as there is nothing much in this activity
 * */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    private lateinit var scaleAnimator: ValueAnimator
    private val handler = Handler()
    private val splashTime = 3000L
    private val animationDuration = 1500L
    private val nextScreenAction = Runnable {
        viewModel.setGenreList().observe(this, Observer {
            moveToNextScreen()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAnimation()
        viewModel.updateGenres()
        handler.postDelayed(nextScreenAction, splashTime)
    }

    private fun setAnimation() {
        scaleAnimator = ValueAnimator.ofFloat(0.85f, 1.15f)
        scaleAnimator.duration = animationDuration
        scaleAnimator.repeatMode = REVERSE
        scaleAnimator.repeatCount = INFINITE
        scaleAnimator.addUpdateListener { value ->
            binding.tvAppName.scaleX = value.animatedValue as Float
            binding.tvAppName.scaleY = value.animatedValue as Float
        }
        scaleAnimator.start()
    }

    private fun moveToNextScreen() {
        scaleAnimator.cancel()
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onDestroy() {
        handler.removeCallbacks(nextScreenAction)
        scaleAnimator.cancel()
        super.onDestroy()
    }

    override fun getLayoutRes() = R.layout.activity_splash

    override fun getViewModelClass(): Class<SplashViewModel> = SplashViewModel::class.java
}