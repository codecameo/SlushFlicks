package com.sifat.slushflicks

import com.sifat.slushflicks.data.impl.DataManagerImplTest
import com.sifat.slushflicks.db.RoomConverterTest
import com.sifat.slushflicks.model.GenreModelTest
import com.sifat.slushflicks.ui.helper.HelperSuite
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.platform.runner.JUnitPlatform
import org.junit.platform.suite.api.SelectClasses
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(JUnitPlatform::class)
@SelectClasses(
    value = [
        DataManagerImplTest::class,
        RoomConverterTest::class,
        GenreModelTest::class,
        HelperSuite::class]
)
class AppJunit5Suite