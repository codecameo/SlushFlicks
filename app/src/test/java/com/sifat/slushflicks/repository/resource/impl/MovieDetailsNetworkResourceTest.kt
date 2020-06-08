package com.sifat.slushflicks.repository.resource.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_DETAIL_API_TAG
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.home.movie.MovieServiceFake
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.repository.resource.impl.MovieDetailsNetworkResource.RequestModel
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.helper.getMovieDetails
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.utils.any
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getMovieDetailsTestModel
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsNetworkResourceTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: MovieDetailsNetworkResource
    private lateinit var movieService: MovieServiceFake
    private lateinit var networkStateManager: NetworkStateManager
    private lateinit var dataManager: DataManager
    private val movieId = 603L

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        movieService = MovieServiceFake(Gson())
        networkStateManager = mock(NetworkStateManager::class.java)
        sut = MovieDetailsNetworkResource(
            dataManager = dataManager,
            movieService = movieService,
            jobManager = mock(JobManager::class.java),
            networkStateManager = networkStateManager,
            request = RequestModel("key", movieId),
            dispatcher = Dispatchers.Main
        )
    }

    @Test
    fun testMovieDetailsSuccessResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        val movieModel = getMovieDetails(getMovieDetailsTestModel())
        val live = MutableLiveData<MovieModel>()
        live.value = movieModel
        `when`(dataManager.getMovieDetails(movieId)).thenReturn(live)
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                sut.asLiveData().getOrAwaitValue() as Success<*>
                verify(dataManager, single()).getMovieDetails(anyLong())
                verify(dataManager, single()).updateMovieDetails(any())
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testMovieDetailsSuccessResponseNoInternet() {
        `when`(networkStateManager.isOnline()).thenReturn(false)
        val movieModel = getMovieDetails(getMovieDetailsTestModel())
        val live = MutableLiveData<MovieModel>()
        live.value = movieModel
        `when`(dataManager.getMovieDetails(movieId)).thenReturn(live)
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as Success<MovieModel>
                kotlin.test.assertEquals(movieModel, actual.dataResponse.data)
                verify(dataManager, single()).getMovieDetails(anyLong())
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testMovieDetailsErrorResponseNoInternet() {
        `when`(networkStateManager.isOnline()).thenReturn(false)
        val live = MutableLiveData<MovieModel>()
        live.value = null
        `when`(dataManager.getMovieDetails(movieId)).thenReturn(live)
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as Success<MovieModel>
                assertNull(actual.dataResponse.data)
                verify(dataManager, single()).getMovieDetails(anyLong())
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testMovieDetailsSuccessResponseUnauth() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        movieService.errorCode = UNAUTHORIZED
        val movieModel = getMovieDetails(getMovieDetailsTestModel())
        val live = MutableLiveData<MovieModel>()
        live.value = movieModel
        `when`(dataManager.getMovieDetails(movieId)).thenReturn(live)
        Assertions.assertDoesNotThrow {
            val sutLive = sut.asLiveData()
            val initVal = sutLive.getOrAwaitValue() as Success<MovieModel>
            kotlin.test.assertEquals(movieModel, initVal.dataResponse.data)
            verify(dataManager, single()).getMovieDetails(anyLong())
            val laterVal = sutLive.getOrAwaitValue() as Error<MovieModel>
            laterVal.dataResponse.run {
                kotlin.test.assertEquals(statusCode, UNAUTHORIZED)
                kotlin.test.assertNotNull(errorMessage)
                kotlin.test.assertEquals(MOVIE_DETAIL_API_TAG, apiTag)
            }
            verifyNoMoreInteractions(dataManager)
        }
    }

    @Test
    fun testMovieDetailsSuccessResponseNoResource() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        movieService.errorCode = RESOURCE_NOT_FOUND
        val movieModel = getMovieDetails(getMovieDetailsTestModel())
        val live = MutableLiveData<MovieModel>()
        live.value = movieModel
        `when`(dataManager.getMovieDetails(movieId)).thenReturn(live)
        Assertions.assertDoesNotThrow {
            val sutLive = sut.asLiveData()
            val initVal = sutLive.getOrAwaitValue() as Success<MovieModel>
            kotlin.test.assertEquals(movieModel, initVal.dataResponse.data)
            verify(dataManager, single()).getMovieDetails(anyLong())
            val laterVal = sutLive.getOrAwaitValue() as Error<MovieModel>
            laterVal.dataResponse.run {
                kotlin.test.assertEquals(statusCode, RESOURCE_NOT_FOUND)
                kotlin.test.assertNotNull(errorMessage)
                kotlin.test.assertEquals(MOVIE_DETAIL_API_TAG, apiTag)
            }
            verifyNoMoreInteractions(dataManager)
        }
    }
}