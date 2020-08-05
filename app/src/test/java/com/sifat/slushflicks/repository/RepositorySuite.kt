package com.sifat.slushflicks.repository

import com.sifat.slushflicks.repository.genre.impl.GenreRepositoryImplTest
import com.sifat.slushflicks.repository.movie.impl.*
import com.sifat.slushflicks.repository.tv.impl.TvDetailsRepositoryImplTest
import com.sifat.slushflicks.repository.tv.impl.TvHomeRepositoryImplTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    value = [
        GenreRepositoryImplTest::class,
        TvHomeRepositoryImplTest::class,
        MovieHomeRepositoryImplTest::class,
        MovieDetailsRepositoryImplTest::class,
        TvDetailsRepositoryImplTest::class,
        NowPlayingRepositoryImplTest::class,
        PopularMovieRepositoryImplTest::class,
        TopRatedMovieRepositoryImplTest::class,
        TrendingMovieRepositoryImplTest::class,
        UpcomingMovieRepositoryImplTest::class
    ]
)
class RepositorySuite