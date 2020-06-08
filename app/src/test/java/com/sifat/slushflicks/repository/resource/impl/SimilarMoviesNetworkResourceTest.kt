package com.sifat.slushflicks.repository.resource.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.home.movie.MovieServiceFake
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.repository.resource.impl.SimilarMoviesNetworkResource.RequestModel
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getGenreMap
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
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
class SimilarMoviesNetworkResourceTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: SimilarMoviesNetworkResource
    private lateinit var movieService: MovieServiceFake
    private lateinit var networkStateManager: NetworkStateManager
    private lateinit var dataManager: DataManager
    private val movieId = 603L
    private val apiTag = "apiTag"
    private val label = "label"
    private val key = "key"

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        movieService = MovieServiceFake(Gson())
        networkStateManager = mock(NetworkStateManager::class.java)
        sut = SimilarMoviesNetworkResource(
            dataManager = dataManager,
            movieService = movieService,
            jobManager = mock(JobManager::class.java),
            networkStateManager = networkStateManager,
            requestModel = RequestModel(key, apiTag, movieId, label),
            dispatcher = Dispatchers.Main
        )
    }

    @Test
    fun testSimilarMovieSuccessResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        `when`(dataManager.getGenres()).thenReturn(getGenreMap())
        assertDoesNotThrow {
            val actual = sut.asLiveData().getOrAwaitValue() as Success<*>
            assertNotNull(actual.dataResponse)
            verify(dataManager, single()).getGenres()
            verifyNoMoreInteractions(dataManager)
        }
    }

    @Test
    fun testSimilarMovieErrorResponseNoInternet() {
        `when`(networkStateManager.isOnline()).thenReturn(false)
        assertDoesNotThrow {
            val actual = sut.asLiveData().getOrAwaitValue() as Error<*>
            assertNotNull(actual.dataResponse)
            actual.dataResponse.run {
                assertEquals(INTERNAL_ERROR, statusCode)
                assertEquals(this@SimilarMoviesNetworkResourceTest.apiTag, apiTag)
                assertNull(errorMessage)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testErrorUnauthResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        movieService.errorCode = UNAUTHORIZED
        assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as Error<*>
            error.dataResponse.run {
                assertEquals(UNAUTHORIZED, statusCode)
                assertNotNull(errorMessage)
                assertEquals(this@SimilarMoviesNetworkResourceTest.apiTag, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testErrorNoResourceResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        movieService.errorCode = RESOURCE_NOT_FOUND
        assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as Error<*>
            error.dataResponse.run {
                assertEquals(RESOURCE_NOT_FOUND, statusCode)
                assertNotNull(errorMessage)
                assertEquals(this@SimilarMoviesNetworkResourceTest.apiTag, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }
}