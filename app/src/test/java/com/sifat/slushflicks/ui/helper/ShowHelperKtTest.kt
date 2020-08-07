package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.ui.base.ListViewState
import com.sifat.slushflicks.ui.base.ListViewState.VIEW
import com.sifat.slushflicks.ui.home.adapter.model.ShowListModel
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.LOADING_MODEL_COUNT
import com.sifat.slushflicks.utils.getGenreMap
import com.sifat.slushflicks.utils.getShowList
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class ShowHelperKtTest {

    @Test
    internal fun testShowListLoadingModels() {
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

    @Test
    internal fun testShowListViewModels() {
        // Arrange
        val list = getShowList()
        // Act
        val expected = getShowListModel(list)
        // Assert
        for (index in list.indices) {
            assertEquals(list[index], expected[index].data)
            assertEquals(VIEW, expected[index].state)
        }
    }

    @Test
    internal fun testNullShowListViewModels() {
        // Arrange
        // Act
        val expected = getShowListModel(null)
        // Assert
        assertEquals(emptyList<ShowListModel>(), expected)
    }

    @Test
    internal fun testGenreListModels() {
        // Arrange
        val genreMap = getGenreMap()
        val genres = mutableListOf<Long>(1, 2, 4)
        // Act
        val actual = getGenresModels(genres, genreMap)
        // Assert
        for (index in genres.indices) {
            assertEquals(genres[index], actual[index].id)
            assertEquals(genreMap[genres[index]], actual[index].name)
        }
    }

    @Test
    internal fun testUnavailableGenreListModels() {
        // Arrange
        val genreMap = getGenreMap()
        val genres = mutableListOf<Long>(1, 2, 7)
        // Act
        val actual = getGenresModels(genres, genreMap)
        // Assert
        for (index in genres.indices) {
            assertEquals(genres[index], actual[index].id)
            assertEquals(genreMap[genres[index]] ?: EMPTY_STRING, actual[index].name)
        }
    }
}