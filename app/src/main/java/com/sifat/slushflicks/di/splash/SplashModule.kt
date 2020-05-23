package com.sifat.slushflicks.di.splash

import com.sifat.slushflicks.api.home.genre.GenreService
import com.sifat.slushflicks.repository.genre.GenreRepository
import com.sifat.slushflicks.repository.genre.impl.GenreRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [InnerModule::class])
object SplashModule {

    @JvmStatic
    @SplashScope
    @Provides
    fun getMovieService(retrofit: Retrofit): GenreService =
        retrofit.create(GenreService::class.java)
}

@Module
abstract class InnerModule {
    @SplashScope
    @Binds
    abstract fun providesGenreRepository(genreRepositoryImpl: GenreRepositoryImpl): GenreRepository
}
