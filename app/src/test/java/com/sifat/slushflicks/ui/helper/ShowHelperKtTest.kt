package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.ui.base.ListViewState
import com.sifat.slushflicks.ui.home.adapter.model.ShowListModel
import com.sifat.slushflicks.utils.LOADING_MODEL_COUNT
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ShowHelperKtTest {

    @Test
    fun testShowListLoadingModels() {
        // Arrange
        val expected = mutableListOf<ShowListModel>()
        for (count in 0..LOADING_MODEL_COUNT) {
            expected.add(ShowListModel(null, ListViewState.LOADING))
        }
        //Act
        val actual = getShowListLoadingModels()
        //Assert
        assertEquals(expected, actual)
    }
}