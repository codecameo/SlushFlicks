package com.sifat.slushflicks.di.app

import com.sifat.slushflicks.di.home.HomeFragmentBuilderModule
import com.sifat.slushflicks.di.home.HomeModule
import com.sifat.slushflicks.di.home.HomeScope
import com.sifat.slushflicks.di.home.HomeViewModelModule
import com.sifat.slushflicks.di.splash.SplashModule
import com.sifat.slushflicks.di.splash.SplashScope
import com.sifat.slushflicks.di.splash.SplashViewModelModule
import com.sifat.slushflicks.ui.home.HomeActivity
import com.sifat.slushflicks.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class, HomeViewModelModule::class, HomeFragmentBuilderModule::class])
    internal abstract fun bindHomeActivity(): HomeActivity

    @SplashScope
    @ContributesAndroidInjector(modules = [SplashModule::class, SplashViewModelModule::class])
    internal abstract fun bindSplashActivity(): SplashActivity

}