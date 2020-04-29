package com.sifat.slushflicks.ui.home

import android.os.Bundle
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ActivityHomeBinding
import com.sifat.slushflicks.ui.base.BaseActivity
import com.sifat.slushflicks.ui.home.movie.fragment.HomeMovieFragment
import com.sifat.slushflicks.ui.home.viewmodel.HomeViewModel

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
