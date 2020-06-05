package com.sifat.slushflicks.repository.resource

import com.sifat.slushflicks.repository.resource.impl.GenreNetworkResourceTest
import com.sifat.slushflicks.repository.resource.impl.TvCastNetworkResourceTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    value = [
        TvCastNetworkResourceTest::class,
        GenreNetworkResourceTest::class]
)
class ResourceSuite