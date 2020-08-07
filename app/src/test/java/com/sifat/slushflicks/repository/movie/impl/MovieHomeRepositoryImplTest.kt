package com.sifat.slushflicks.repository.movie.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.CollectionModel
import com.sifat.slushflicks.ui.state.DataErrorResponse
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.getCollectionModel
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieHomeRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()
    lateinit var sut: MovieHomeRepositoryImpl
    lateinit var dataManager: DataManager

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        sut = MovieHomeRepositoryImpl(dataManager)
    }

    @Test
    fun testMovieCollectionSuccess() {
        //Arrange
        val live = MutableLiveData<DataState<List<CollectionModel>>>()
        val dataResponse = DataSuccessResponse(data = getCollectionModel())
        live.value = Success(dataResponse)
        `when`(dataManager.getMovieCollections()).thenReturn(live)
        assertDoesNotThrow {
            // Act
            val actual = sut.getMovieCollection().getOrAwaitValue() as Success<*>
            // Assert
            assertEquals(dataResponse, actual.dataResponse)
            verify(dataManager, single()).getMovieCollections()
            verifyNoMoreInteractions(dataManager)
        }
    }

    @Test
    fun testMovieCollectionError() {
        //Arrange
        val live = MutableLiveData<DataState<List<CollectionModel>>>()
        val dataResponse = DataErrorResponse<List<CollectionModel>>()
        live.value = Error(dataResponse)
        `when`(dataManager.getMovieCollections()).thenReturn(live)
        assertDoesNotThrow {
            // Act
            val actual = sut.getMovieCollection().getOrAwaitValue() as Error<*>
            // Assert
            assertEquals(dataResponse, actual.dataResponse)
            verify(dataManager, single()).getMovieCollections()
            verifyNoMoreInteractions(dataManager)
        }
    }
}