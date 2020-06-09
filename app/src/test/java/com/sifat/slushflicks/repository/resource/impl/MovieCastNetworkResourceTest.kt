package com.sifat.slushflicks.repository.resource.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiTag
import com.sifat.slushflicks.api.StatusCode
import com.sifat.slushflicks.api.home.movie.MovieServiceFake
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.CastModel
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.capture
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentCaptor.forClass
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieCastNetworkResourceTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: MovieCastNetworkResource
    private lateinit var movieService: MovieServiceFake
    private lateinit var jobManager: JobManager
    private lateinit var networkStateManager: NetworkStateManager
    private lateinit var dataManager: DataManager
    private val movieId = 100L

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        movieService = MovieServiceFake(Gson())
        networkStateManager = mock(NetworkStateManager::class.java)
        jobManager = mock(JobManager::class.java)
        sut = MovieCastNetworkResource(
            dataManager = dataManager,
            movieService = movieService,
            jobManager = jobManager,
            networkStateManager = networkStateManager,
            requestModel = MovieCastNetworkResource.RequestModel("key", movieId),
            dispatcher = Dispatchers.Main
        )
    }

    @Test
    fun testCastSuccessResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                sut.asLiveData().getOrAwaitValue() as DataState.Success<*>
                verify(dataManager, single()).updateMovieDetails(
                    anyList(),
                    ArgumentMatchers.anyLong()
                )
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testCastSuccessResponseCheckMaxListCount() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as DataState.Success<*>
                kotlin.test.assertEquals(16, actual.dataResponse.data)
                val listClass: Class<List<CastModel>> = List::class.java as Class<List<CastModel>>
                val argument: ArgumentCaptor<List<CastModel>> = forClass(listClass)
                verify(dataManager, single()).updateMovieDetails(
                    capture(argument), ArgumentMatchers.anyLong()
                )
                kotlin.test.assertEquals(10, argument.value.size)
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testCastErrorNoInternetResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(false)
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
                kotlin.test.assertEquals(StatusCode.INTERNAL_ERROR, error.dataResponse.statusCode)
                verifyZeroInteractions(dataManager)
            }
        }
    }

    @Test
    fun testCastErrorUnauthResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        movieService.errorCode = StatusCode.UNAUTHORIZED
        Assertions.assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                kotlin.test.assertEquals(StatusCode.UNAUTHORIZED, statusCode)
                kotlin.test.assertNotNull(errorMessage)
                kotlin.test.assertEquals(ApiTag.MOVIE_CREDITS_API_TAG, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testCastErrorNoResourceResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        movieService.errorCode = StatusCode.RESOURCE_NOT_FOUND
        Assertions.assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                kotlin.test.assertEquals(StatusCode.RESOURCE_NOT_FOUND, statusCode)
                kotlin.test.assertNotNull(errorMessage)
                kotlin.test.assertEquals(ApiTag.MOVIE_CREDITS_API_TAG, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }
}