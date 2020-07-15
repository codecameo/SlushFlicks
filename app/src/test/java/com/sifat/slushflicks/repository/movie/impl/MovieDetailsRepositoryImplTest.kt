package com.sifat.slushflicks.repository.movie.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.home.movie.MovieServiceFake
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.repository.movie.MovieDetailsRepository
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.utils.any
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.movieDetailsResponse
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class MovieDetailsRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    internal lateinit var sut: MovieDetailsRepository
    private lateinit var manager: DataManager
    private lateinit var service: MovieServiceFake
    private lateinit var apiKey: String
    private lateinit var networkState: NetworkStateManager
    private lateinit var jobManager: JobManager

    @Before
    fun setup() {
        manager = mock(DataManager::class.java)
        service = MovieServiceFake(Gson())
        jobManager = mock(JobManager::class.java)
        networkState = mock(NetworkStateManager::class.java)
        apiKey = "apiKey"
        sut = MovieDetailsRepositoryImpl(
            dataManager = manager,
            movieService = service,
            apiKey = apiKey,
            jobManager = jobManager,
            networkStateManager = networkState,
            dispatcher = Dispatchers.Main
        )
    }

    @Test
    fun testMovieDetailsSuccess() {
        // Arrange
        val expected = Gson().fromJson(movieDetailsResponse, MovieModel::class.java)
        val live = MutableLiveData<MovieModel>()
        live.value = expected
        `when`(manager.getMovieDetails(anyLong())).thenReturn(live)
        `when`(networkState.isOnline()).thenReturn(true)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieDetails(expected.id).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected)
                verify(manager, single()).getMovieDetails(expected.id)
                verify(manager, single()).updateMovieDetails(any())
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testMovieDetailsNoNetwork() {
        // Arrange
        val expected = Gson().fromJson(movieDetailsResponse, MovieModel::class.java)
        val live = MutableLiveData<MovieModel>()
        live.value = expected
        `when`(manager.getMovieDetails(anyLong())).thenReturn(live)
        `when`(networkState.isOnline()).thenReturn(false)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieDetails(expected.id).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected)
                verify(manager, single()).getMovieDetails(expected.id)
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testMovieDetailsErrorUnauth() {
        // Arrange
        service.errorCode = UNAUTHORIZED
        val expected = Gson().fromJson(movieDetailsResponse, MovieModel::class.java)
        val live = MutableLiveData<MovieModel>()
        live.value = expected
        `when`(manager.getMovieDetails(anyLong())).thenReturn(live)
        `when`(networkState.isOnline()).thenReturn(true)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieDetails(expected.id).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected)
                verify(manager, single()).getMovieDetails(expected.id)
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testMovieDetailsErrorResNotFound() {
        // Arrange
        service.errorCode = RESOURCE_NOT_FOUND
        val expected = Gson().fromJson(movieDetailsResponse, MovieModel::class.java)
        val live = MutableLiveData<MovieModel>()
        live.value = expected
        `when`(manager.getMovieDetails(anyLong())).thenReturn(live)
        `when`(networkState.isOnline()).thenReturn(true)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieDetails(expected.id).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected)
                verify(manager, single()).getMovieDetails(expected.id)
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun getMovieVideo() {
        // Arrange
        //Act
        //Assert
    }

    @Test
    fun getMovieCast() {
        // Arrange
        //Act
        //Assert
    }

    @Test
    fun getSimilarMovies() {
        // Arrange
        //Act
        //Assert
    }

    @Test
    fun getRecommendationMovies() {
        // Arrange
        //Act
        //Assert
    }

    @Test
    fun getReviews() {
        // Arrange
        //Act
        //Assert
    }

    @Test
    fun updateRecentMovie() {
        // Arrange
        //Act
        //Assert
    }

    @Test
    fun cancelAllJobs() {
        // Arrange
        //Act
        //Assert
    }
}