package com.sifat.slushflicks.repository.tv.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.state.DataErrorResponse
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.getCollectionModel
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TvHomeRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var sut: TvHomeRepositoryImpl
    lateinit var dataManager: DataManager

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        sut = TvHomeRepositoryImpl(dataManager)
    }

    @Test
    fun testTvShowCollectionSuccess() {
        //Arrange
        val live = MutableLiveData<DataState<List<CollectionModel>>>()
        val dataResponse = DataSuccessResponse(data = getCollectionModel())
        live.value = DataState.Success(dataResponse)
        `when`(dataManager.getTvCollections()).thenReturn(live)
        Assertions.assertDoesNotThrow {
            // Act
            val actual = sut.getTvCollection().getOrAwaitValue() as DataState.Success<*>
            // Assert
            assertEquals(dataResponse, actual.dataResponse)
            verify(dataManager, single()).getTvCollections()
            verifyNoMoreInteractions(dataManager)
        }
    }

    @Test
    fun testTvShowCollectionError() {
        //Arrange
        val live = MutableLiveData<DataState<List<CollectionModel>>>()
        val dataResponse = DataErrorResponse<List<CollectionModel>>()
        live.value = DataState.Error(dataResponse)
        `when`(dataManager.getTvCollections()).thenReturn(live)
        Assertions.assertDoesNotThrow {
            // Act
            val actual = sut.getTvCollection().getOrAwaitValue() as DataState.Error<*>
            // Assert
            assertEquals(dataResponse, actual.dataResponse)
            verify(dataManager, single()).getTvCollections()
            verifyNoMoreInteractions(dataManager)
        }
    }
}