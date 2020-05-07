package com.sifat.slushflicks.di.home.movie

import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.di.home.HomeScope
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MovieModule {
    @HomeScope
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}