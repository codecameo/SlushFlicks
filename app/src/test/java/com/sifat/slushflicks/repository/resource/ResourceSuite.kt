package com.sifat.slushflicks.repository.resource

import com.sifat.slushflicks.repository.resource.impl.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    value = [
        TvCastNetworkResourceTest::class,
        GenreNetworkResourceTest::class,
        TvVideoNetworkResourceTest::class,
        TvDetailsNetworkResourceTest::class,
        SimilarTvShowNetworkResourceTest::class,
        TrendingTvListResourceTest::class,
        TvListNetworkResourceTest::class
    ]
)
class ResourceSuite