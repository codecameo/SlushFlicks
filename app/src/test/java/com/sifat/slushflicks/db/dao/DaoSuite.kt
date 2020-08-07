package com.sifat.slushflicks.db.dao

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.runner.RunWith
import org.junit.runners.Suite

@ExperimentalCoroutinesApi
@RunWith(Suite::class)
@Suite.SuiteClasses(
    value = [GenreDaoTest::class,
        MovieDaoTest::class,
        TvDaoTest::class,
        MovieCollectionDaoTest::class,
        TvCollectionDaoTest::class
    ]
)
class DaoSuite