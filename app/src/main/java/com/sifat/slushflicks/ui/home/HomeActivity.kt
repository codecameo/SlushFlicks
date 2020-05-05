package com.sifat.slushflicks.ui.home

import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ActivityHomeBinding
import com.sifat.slushflicks.ui.base.BaseActivity
import com.sifat.slushflicks.ui.home.viewmodel.HomeViewModel


class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>() {

    override fun getLayoutRes() = R.layout.activity_home
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupNavigation()
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment?
        NavigationUI.setupWithNavController(
            binding.bottomNavigation,
            navHostFragment!!.navController
        )
    }
}
