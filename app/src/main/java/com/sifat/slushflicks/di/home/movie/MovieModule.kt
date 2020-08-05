package com.sifat.slushflicks.di.home.movie

import com.sifat.slushflicks.api.home.movie.MovieService
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.repository.movie.MovieHomeRepository
import com.sifat.slushflicks.repository.movie.impl.MovieHomeRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit

@Module(includes = [InnerModule::class])
abstract class MovieModule {

    @MovieScope
    @Binds
    abstract fun bindMovieHomeRepository(movieHomeRepositoryImpl: MovieHomeRepositoryImpl): MovieHomeRepository
}

@Module
object InnerModule {

    @JvmStatic
    @MovieScope
    @Provides
    fun provideMovieService(retrofit: Retrofit): MovieService =
        retrofit.create(MovieService::class.java)

    @JvmStatic
    @MovieScope
    @Provides
    fun provideMovieDispatcher(): CoroutineDispatcher = Dispatchers.IO

    // Provide new instance of job manager when needed
    @JvmStatic
    @Provides
    fun provideJobManager() = JobManager()
}