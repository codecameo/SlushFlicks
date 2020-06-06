package com.sifat.slushflicks.repository.resource.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.util.getOrAwaitValue
import com.sifat.slushflicks.utils.getGenreList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GenreNetworkResourceTest {

    private lateinit var sut: GenreNetworkResource
    private lateinit var manager: DataManager

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        manager = mock(DataManager::class.java)
        sut = GenreNetworkResource(manager, Dispatchers.Main)
    }

    @Test
    fun testCacheDataSuccessEmptyList() {
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                // Arrange
                `when`(manager.loadGenres()).thenReturn(emptyList())
                //Act
                val actual =
                    sut.asLiveData().getOrAwaitValue() as Success<List<GenreModel>>
                //Assert
                assertEquals(emptyList<GenreModel>(), actual.dataResponse.data)
                assertNull(actual.dataResponse.message)
                assertNull(actual.dataResponse.metaData)
                verify(manager, times(1)).loadGenres()
                verifyNoMoreInteractions(manager)
            }
        }
    }

    @Test
    fun testCacheDataSuccess() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val list = getGenreList()
        `when`(manager.loadGenres()).thenReturn(list)
        //Act
        val actual = sut.asLiveData().getOrAwaitValue() as Success<List<GenreModel>>
        //Assert
        assertEquals(list, actual.dataResponse.data)
        assertNull(actual.dataResponse.message)
        assertNull(actual.dataResponse.metaData)
        verify(manager, times(1)).loadGenres()
        verifyNoMoreInteractions(manager)
    }

    @Test
    fun testCacheDataFailure() {
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                // Arrange
                `when`(manager.loadGenres()).thenReturn(null)
                //Act
                val actual = sut.asLiveData().getOrAwaitValue() as DataState.Error<List<GenreModel>>
                assertEquals(INTERNAL_ERROR, actual.dataResponse.statusCode)
                assertNull(actual.dataResponse.errorMessage)
                verify(manager, times(1)).loadGenres()
                verifyNoMoreInteractions(manager)
            }
        }
    }
}