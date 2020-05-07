package com.sifat.slushflicks.ui.home

import android.os.Bundle
import android.view.MenuItem
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.sifat.slushflicks.R
import com.sifat.slushflicks.databinding.ActivityHomeBinding
import com.sifat.slushflicks.ui.base.BaseActivity
import com.sifat.slushflicks.ui.home.viewmodel.HomeViewModel


class HomeActivity : BaseActivity<ActivityHomeBinding, HomeViewModel>(),
    BottomNavigationView.OnNavigationItemReselectedListener {

    override fun getLayoutRes() = R.layout.activity_home
    override fun getViewModelClass(): Class<HomeViewModel> = HomeViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initListeners()
        setupNavigation()
    }

    private fun initListeners() {
        // To stop reloading fragment on item reselection
        binding.bottomNavigation.setOnNavigationItemReselectedListener(this)
    }

    private fun setupNavigation() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment?
        NavigationUI.setupWithNavController(
            binding.bottomNavigation,
            navHostFragment!!.navController
        )
    }

    override fun onNavigationItemReselected(item: MenuItem) {
        // Empty Implementation
    }
}
