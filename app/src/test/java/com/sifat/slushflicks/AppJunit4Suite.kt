package com.sifat.slushflicks

import com.sifat.slushflicks.data.impl.DataSuite
import com.sifat.slushflicks.db.dao.DaoSuite
import com.sifat.slushflicks.repository.resource.ResourceSuite
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    value = [
        DaoSuite::class,
        ResourceSuite::class,
        DataSuite::class]
)
class AppJunit4Suite