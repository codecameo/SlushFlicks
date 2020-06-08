package com.sifat.slushflicks.repository.resource.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_VIDEO_API_TAG
import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.api.home.movie.MovieServiceFake
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.repository.resource.impl.MovieVideoNetworkResource.RequestModel
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.any
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieVideoNetworkResourceTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: MovieVideoNetworkResource
    private lateinit var movieService: MovieServiceFake
    private lateinit var jobManager: JobManager
    private lateinit var networkStateManager: NetworkStateManager
    private lateinit var dataManager: DataManager
    private val movieId = 603L

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        movieService = MovieServiceFake(Gson())
        networkStateManager = mock(NetworkStateManager::class.java)
        jobManager = mock(JobManager::class.java)
        sut = MovieVideoNetworkResource(
            dataManager = dataManager,
            movieService = movieService,
            jobManager = jobManager,
            networkStateManager = networkStateManager,
            requestModel = RequestModel("key", movieId),
            dispatcher = Main
        )
    }

    @Test
    fun testVideoSuccessResponseWithYoutubeTrailer() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as DataState.Success<*>
                assertNotNull(actual.dataResponse.data)
                verify(dataManager, single()).updateMovieDetails(
                    any<VideoApiModel>(),
                    ArgumentMatchers.anyLong()
                )
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testVideoSuccessResponseWithNoYoutubeTrailer() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        movieService.noYoutubeVideo = true
        Assertions.assertDoesNotThrow {
            val actual = sut.asLiveData().getOrAwaitValue() as DataState.Success<*>
            assertNull(actual.dataResponse.data)
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testVideoErrorNoInternetResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(false)
        Assertions.assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            assertEquals(INTERNAL_ERROR, error.dataResponse.statusCode)
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testVideoErrorUnauthResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        movieService.errorCode = UNAUTHORIZED
        Assertions.assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                assertEquals(UNAUTHORIZED, statusCode)
                assertNotNull(errorMessage)
                assertNotNull(MOVIE_VIDEO_API_TAG, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testVideoErrorNoResourceResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        movieService.errorCode = RESOURCE_NOT_FOUND
        Assertions.assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                assertEquals(RESOURCE_NOT_FOUND, statusCode)
                assertNotNull(errorMessage)
                assertEquals(MOVIE_VIDEO_API_TAG, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }
}