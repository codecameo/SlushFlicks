package com.sifat.slushflicks.di.app

import com.sifat.slushflicks.di.details.DetailsModule
import com.sifat.slushflicks.di.details.DetailsScope
import com.sifat.slushflicks.di.details.DetailsViewModelModule
import com.sifat.slushflicks.di.home.HomeFragmentBuilderModule
import com.sifat.slushflicks.di.home.HomeModule
import com.sifat.slushflicks.di.home.HomeScope
import com.sifat.slushflicks.di.home.HomeViewModelModule
import com.sifat.slushflicks.di.splash.SplashModule
import com.sifat.slushflicks.di.splash.SplashScope
import com.sifat.slushflicks.di.splash.SplashViewModelModule
import com.sifat.slushflicks.ui.details.MovieDetailsActivity
import com.sifat.slushflicks.ui.home.HomeActivity
import com.sifat.slushflicks.ui.splash.SplashActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @SplashScope
    @ContributesAndroidInjector(modules = [SplashModule::class, SplashViewModelModule::class])
    internal abstract fun bindSplashActivity(): SplashActivity

    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class, HomeViewModelModule::class, HomeFragmentBuilderModule::class])
    internal abstract fun bindHomeActivity(): HomeActivity

    @DetailsScope
    @ContributesAndroidInjector(modules = [DetailsModule::class, DetailsViewModelModule::class])
    internal abstract fun bindDetailsActivity(): MovieDetailsActivity

}