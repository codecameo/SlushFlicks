package com.sifat.slushflicks.data.impl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    value = [
        LocalDataManagerImplTest::class,
        DatabaseManagerImplTest::class
    ]
)
class DataSuite