package com.example.slushflicks.ui.home

import android.os.Bundle
import com.example.slushflicks.R
import com.example.slushflicks.databinding.ActivityHomeBinding
import com.example.slushflicks.ui.base.BaseActivity
import com.example.slushflicks.ui.home.movie.fragment.HomeMovieFragment
import com.example.slushflicks.ui.home.viewmodel.HomeViewModel

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun getLayoutRes() = R.layout.activity_home
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initVariables()

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, HomeMovieFragment())
            .commit()


    }

    private fun initVariables() {

    }
}
