package com.sifat.slushflicks.repository

import com.sifat.slushflicks.repository.genre.impl.GenreRepositoryImplTest
import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(value = [GenreRepositoryImplTest::class])
class RepositorySuite