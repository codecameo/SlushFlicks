package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.base.ListViewState.LOADING
import com.sifat.slushflicks.ui.base.ListViewState.VIEW
import com.sifat.slushflicks.ui.home.adapter.model.CollectionListModel
import com.sifat.slushflicks.utils.LOADING_MODEL_COUNT
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNotEquals
import org.junit.jupiter.api.Test
import java.lang.System.identityHashCode

internal class CollectionListHelperKtTest {

    @Test
    fun testCollectionListLoadingModel() {
        // Arrange
        val expected = mutableListOf<CollectionListModel>()
        for (count in 0..LOADING_MODEL_COUNT) {
            expected.add(CollectionListModel(null, LOADING))
        }
        //Act
        val actual = getCollectionListLoadingModel()
        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun testCollectionListModel() {
        // Arrange
        val collections = mutableListOf<CollectionModel>()
        collections.add(CollectionModel("name1", "label1"))
        collections.add(CollectionModel("name2", "label2"))
        collections.add(CollectionModel("name3", "label3"))
        //Act
        val actual = getCollectionListModel(collections)
        //Assert
        for (index in actual.indices) {
            assertEquals(VIEW, actual[index].state)
            assertEquals(collections[index], actual[index].data)
            assertEquals(false, actual[index].data?.isEnable)
        }
    }

    @Test
    fun testCopiedCollectionListModel() {
        // Arrange
        val collections = mutableListOf<CollectionListModel>()
        collections.add(CollectionListModel(CollectionModel("name1", "label1"), VIEW))
        collections.add(CollectionListModel(CollectionModel("name2", "label2", true), LOADING))
        collections.add(CollectionListModel(CollectionModel("name3", "label3"), VIEW))
        //Act
        val actual = getCopiedCollectionListModel(collections)
        //Assert
        for (index in actual.indices) {
            assertEquals(collections[index].state, actual[index].state)
            assertEquals(collections[index].data, actual[index].data)
            assertNotEquals(collections[index].hashCode(), actual[index].hashCode())
            assertNotEquals(
                identityHashCode(collections[index].data),
                identityHashCode(actual[index].data)
            )
            assertEquals(collections[index].data?.isEnable, actual[index].data?.isEnable)
        }
    }
}