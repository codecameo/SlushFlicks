package com.sifat.slushflicks.di.details

import com.sifat.slushflicks.repository.movie.MovieDetailsRepository
import com.sifat.slushflicks.repository.movie.impl.MovieDetailsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
abstract class MovieDetailsBindModule {
    @MovieDetailsScope
    @Binds
    abstract fun provideDetailsRepository(movieDetailsRepositoryImpl: MovieDetailsRepositoryImpl): MovieDetailsRepository
}