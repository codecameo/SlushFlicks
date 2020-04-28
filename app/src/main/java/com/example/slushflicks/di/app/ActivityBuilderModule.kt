package com.example.slushflicks.di.app

import com.example.slushflicks.di.home.HomeFragmentBuilderModule
import com.example.slushflicks.di.home.HomeModule
import com.example.slushflicks.di.home.HomeViewModelModule
import com.example.slushflicks.di.home.HomeScope
import com.example.slushflicks.di.splash.SplashModule
import com.example.slushflicks.di.splash.SplashScope
import com.example.slushflicks.di.splash.SplashViewModelModule
import com.example.slushflicks.ui.home.HomeActivity
import com.example.slushflicks.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class, HomeViewModelModule::class, HomeFragmentBuilderModule::class])
    internal abstract fun bindHomeActivity(): HomeActivity

    @SplashScope
    @ContributesAndroidInjector(modules = [SplashModule::class, SplashViewModelModule::class])
    internal abstract fun bindSplashActivity() : SplashActivity

}