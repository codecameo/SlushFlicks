package com.sifat.slushflicks.repository.resource

import com.sifat.slushflicks.repository.resource.impl.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    value = [
        GenreNetworkResourceTest::class,
        TvCastNetworkResourceTest::class,
        TvVideoNetworkResourceTest::class,
        TvDetailsNetworkResourceTest::class,
        SimilarTvShowNetworkResourceTest::class,
        TrendingTvListResourceTest::class,
        TvListNetworkResourceTest::class,
        MovieCastNetworkResourceTest::class,
        MovieVideoNetworkResourceTest::class,
        MovieDetailsNetworkResourceTest::class,
        SimilarMoviesNetworkResourceTest::class,
        TrendingMovieListResourceTest::class,
        MovieListNetworkResourceTest::class,
        TvGenreNetworkResourceTest::class,
        MovieGenreNetworkResourceTest::class
    ]
)
class ResourceSuite