package com.sifat.slushflicks.ui.splash

import android.animation.ValueAnimator
import android.animation.ValueAnimator.INFINITE
import android.animation.ValueAnimator.REVERSE
import android.app.TaskStackBuilder
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.text.TextUtils
import android.view.animation.AccelerateDecelerateInterpolator
import androidx.lifecycle.Observer
import com.google.firebase.dynamiclinks.FirebaseDynamicLinks
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ActivitySplashBinding
import com.sifat.slushflicks.ui.base.BaseActivity
import com.sifat.slushflicks.ui.details.BaseDetailsActivity.Companion.KEY_SHOW_ID
import com.sifat.slushflicks.ui.details.MovieDetailsActivity
import com.sifat.slushflicks.ui.details.TvDetailsActivity
import com.sifat.slushflicks.ui.home.HomeActivity
import com.sifat.slushflicks.ui.splash.viewmodel.SplashViewModel
import com.sifat.slushflicks.utils.DynamicLinkConst
import com.sifat.slushflicks.utils.DynamicLinkConst.Companion.MOVIE_SHOW_TYPE
import com.sifat.slushflicks.utils.DynamicLinkConst.Companion.SHOW_ID_PARAM
import com.sifat.slushflicks.utils.DynamicLinkConst.Companion.SHOW_TYPE_PARAM


/**
 * Will follow MVVM for splash as there is nothing much in this activity
 * */
class SplashActivity : BaseActivity<ActivitySplashBinding, SplashViewModel>() {

    private var showType: String? = null
    private var showId: Long? = null
    private lateinit var scaleAnimator: ValueAnimator
    private val handler = Handler()
    private val splashTime = 3000L
    private val animationDuration = 1200L
    private val nextScreenAction = Runnable {
        viewModel.setGenreList().observe(this, Observer {
            moveToNextScreen()
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        checkDynamicLink()
        setAnimation()
        viewModel.updateGenres()
        handler.postDelayed(nextScreenAction, splashTime)
    }

    private fun checkDynamicLink() {
        FirebaseDynamicLinks.getInstance()
            .getDynamicLink(intent)
            .addOnCompleteListener(
                this@SplashActivity
            ) { task ->
                var deepLink: Uri? = null
                if (task.isSuccessful && task.result != null) {
                    deepLink = task.result!!.link
                } else if (!TextUtils.isEmpty(intent.dataString)) {
                    deepLink = Uri.parse(intent.dataString)
                }
                deepLink?.let { link ->
                    showId = link.getQueryParameter(SHOW_ID_PARAM)?.toLong()
                    showType = link.getQueryParameter(SHOW_TYPE_PARAM)
                }

            }
    }

    private fun setAnimation() {
        scaleAnimator = ValueAnimator.ofFloat(0.85f, 1.15f)
        scaleAnimator.duration = animationDuration
        scaleAnimator.repeatMode = REVERSE
        scaleAnimator.repeatCount = INFINITE
        scaleAnimator.interpolator = AccelerateDecelerateInterpolator()
        scaleAnimator.addUpdateListener { value ->
            binding.tvAppName.scaleX = value.animatedValue as Float
            binding.tvAppName.scaleY = value.animatedValue as Float
        }
        scaleAnimator.start()
    }

    private fun moveToNextScreen() {
        scaleAnimator.cancel()
        if (showId != null) {
            val intent = when (showType) {
                MOVIE_SHOW_TYPE -> {
                    Intent(this, MovieDetailsActivity::class.java)
                }
                DynamicLinkConst.TV_SERIES_TYPE -> {
                    Intent(this, TvDetailsActivity::class.java)
                }
                else -> {
                    Intent(this, HomeActivity::class.java)
                }
            }
            intent.putExtra(KEY_SHOW_ID, showId!!)
            val taskBuilder = TaskStackBuilder.create(this)
            taskBuilder.addNextIntentWithParentStack(intent)
            taskBuilder.startActivities()
        } else {
            startActivity(Intent(this, HomeActivity::class.java))
        }
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