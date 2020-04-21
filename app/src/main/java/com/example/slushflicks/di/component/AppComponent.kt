package com.example.slushflicks.di.component

import com.example.slushflicks.SlushFlicksApplication
import com.example.slushflicks.di.module.AppModule
import com.example.slushflicks.di.module.builder.ActivityBuilderModule
import com.example.slushflicks.di.scope.AppScope
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@AppScope
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilderModule::class])
interface AppComponent : AndroidInjector<SlushFlicksApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(slushFlicksApplication: SlushFlicksApplication) : Builder

        fun build() : AppComponent
    }
}