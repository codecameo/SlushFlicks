package com.sifat.slushflicks.ui.helper

import org.junit.platform.runner.JUnitPlatform
import org.junit.platform.suite.api.SelectClasses
import org.junit.runner.RunWith

@RunWith(JUnitPlatform::class)
@SelectClasses(
    value = [
        CollectionListHelperKtTest::class,
        VideoHelperKtTest::class,
        ShowHelperKtTest::class,
        TvHelperKtTest::class,
        MovieHelperKtTest::class]
)
class HelperSuite