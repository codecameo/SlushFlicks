package com.sifat.slushflicks.repository.resource

import com.sifat.slushflicks.repository.resource.impl.GenreNetworkResourceTest
import com.sifat.slushflicks.repository.resource.impl.TvCastNetworkResourceTest
import com.sifat.slushflicks.repository.resource.impl.TvDetailsNetworkResourceTest
import com.sifat.slushflicks.repository.resource.impl.TvVideoNetworkResourceTest
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
        TvDetailsNetworkResourceTest::class
    ]
)
class ResourceSuite