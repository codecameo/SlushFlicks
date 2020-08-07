package com.sifat.slushflicks.repository.resource.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiTag.Companion.TV_GENRE_API_TAG
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.home.genre.GenreServiceTestFake
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvGenreNetworkResourceTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: TvGenreNetworkResource
    private lateinit var genreService: GenreServiceTestFake
    private lateinit var networkStateManager: NetworkStateManager
    private lateinit var dataManager: DataManager

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        genreService = GenreServiceTestFake(Gson())
        networkStateManager = mock(NetworkStateManager::class.java)
        sut = TvGenreNetworkResource(
            dataManager = dataManager,
            apiKey = "key",
            genreService = genreService,
            networkStateManager = networkStateManager,
            dispatcher = Main
        )
    }

    @Test
    fun testGenreSuccess() {
        // Arrange
        `when`(networkStateManager.isOnline()).thenReturn(true)
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                sut.asLiveData().getOrAwaitValue() as Success<*>
                // Assert
                verify(dataManager, single()).saveGenre(anyList())
                verify(networkStateManager, single()).isOnline()
                verifyNoMoreInteractions(dataManager)
                verifyNoMoreInteractions(networkStateManager)
            }
        }
    }

    @Test
    fun testGenreErrorNoInternet() {
        // Arrange
        `when`(networkStateManager.isOnline()).thenReturn(false)
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                sut.asLiveData().getOrAwaitValue() as Error<*>
                // Assert
                verify(networkStateManager, single()).isOnline()
                verifyZeroInteractions(dataManager)
                verifyNoMoreInteractions(networkStateManager)
            }
        }
    }

    @Test
    fun testTvGenreErrorUnauthResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        genreService.errorCode = UNAUTHORIZED
        assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                assertEquals(UNAUTHORIZED, statusCode)
                assertNotNull(errorMessage)
                assertEquals(TV_GENRE_API_TAG, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testTvGenreErrorNoResourceResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        genreService.errorCode = RESOURCE_NOT_FOUND
        assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                assertEquals(RESOURCE_NOT_FOUND, statusCode)
                assertNotNull(errorMessage)
                assertEquals(TV_GENRE_API_TAG, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }
}