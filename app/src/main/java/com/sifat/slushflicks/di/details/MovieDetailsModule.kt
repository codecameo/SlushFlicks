package com.sifat.slushflicks.di.details

import com.sifat.slushflicks.api.home.movie.MovieService
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MovieDetailsModule {

    @MovieDetailsScope
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}