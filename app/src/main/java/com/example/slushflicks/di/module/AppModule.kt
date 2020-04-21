package com.example.slushflicks.di.module

import com.example.slushflicks.models.MovieListModel
import dagger.Module
import dagger.Provides

@Module
class AppModule {


    @Provides
    fun provideM() : MovieListModel {
        return MovieListModel("id:001")
    }
}