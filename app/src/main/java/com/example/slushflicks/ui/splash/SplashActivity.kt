package com.example.slushflicks.ui.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.lifecycle.Observer
import com.example.slushflicks.repository.GenreRepository
import com.example.slushflicks.ui.home.HomeActivity
import com.example.slushflicks.ui.state.DataState
import com.example.slushflicks.ui.state.DataSuccessResponse
import dagger.android.DaggerActivity
import dagger.android.support.DaggerAppCompatActivity
import java.util.*
import javax.inject.Inject

/**
 * Will follow MVVM for splash as there is nothing much in this activity
 * */
class SplashActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var genreRepository: GenreRepository
    private val handler = Handler()
    private val nextScreenAction = Runnable {
        genreRepository.setGenreList().observe(this, Observer {response ->
            moveToNextScreen()
        })
    }

    private fun moveToNextScreen() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        genreRepository.updateGenres()
        handler.postDelayed(nextScreenAction, 2000)
    }

    override fun onDestroy() {
        handler.removeCallbacks(nextScreenAction)
        super.onDestroy()
    }
}