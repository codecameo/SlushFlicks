package com.example.slushflicks.di.app

import com.example.slushflicks.SlushFlicksApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector

@AppScope
@Component(modules = [AndroidInjectionModule::class, AppModule::class, ActivityBuilderModule::class, AppBindModule::class])
interface AppComponent : AndroidInjector<SlushFlicksApplication> {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(slushFlicksApplication: SlushFlicksApplication): Builder

        fun build(): AppComponent
    }
}