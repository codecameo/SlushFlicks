package com.example.slushflicks.di.module.builder

import com.example.slushflicks.di.module.home.HomeModule
import com.example.slushflicks.di.module.home.HomeViewModelModule
import com.example.slushflicks.di.scope.HomeScope
import com.example.slushflicks.ui.home.HomeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @HomeScope
    @ContributesAndroidInjector(modules = [HomeModule::class, HomeViewModelModule::class])
    internal abstract fun bindHomeActivity(): HomeActivity

}