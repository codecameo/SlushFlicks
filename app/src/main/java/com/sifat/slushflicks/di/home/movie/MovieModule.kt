package com.sifat.slushflicks.di.home.movie

import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.repository.movie.MovieHomeRepository
import com.sifat.slushflicks.repository.movie.impl.MovieHomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [InnerModule::class])
abstract class MovieModule {

    @MovieScope
    @Binds
    abstract fun bindMovieHomeRepository(movieHomeRepositoryImpl: MovieHomeRepositoryImpl): MovieHomeRepository
}

@Module
class InnerModule {

    @MovieScope
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)
}