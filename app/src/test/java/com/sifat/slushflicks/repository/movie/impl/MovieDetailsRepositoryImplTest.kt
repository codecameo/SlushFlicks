package com.sifat.slushflicks.repository.movie.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiTag
import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.details.model.CreditsApiModel
import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.api.details.model.VideoListApiModel
import com.sifat.slushflicks.api.home.movie.MovieServiceFake
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.CastModel
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.repository.movie.MovieDetailsRepository
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.helper.getMovieMinimalApiModel
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.utils.any
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.api.movieCastResponse
import com.sifat.slushflicks.utils.api.movieSimilarResponse
import com.sifat.slushflicks.utils.api.movieVideoResponse
import com.sifat.slushflicks.utils.getGenreMap
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.movieDetailsResponse
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertNotNull

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
    fun testMovieVideoSuccess() {
        // Arrange
        val videoList = Gson().fromJson(movieVideoResponse, VideoListApiModel::class.java)
        val expected = videoList.results[0].key
        val movieId = 603L
        `when`(networkState.isOnline()).thenReturn(true)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieVideo(movieId).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected)
                verify(manager, single()).updateMovieDetails(any<VideoApiModel>(), anyLong())
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testMovieNoVideoSuccess() {
        // Arrange
        val movieId = 603L
        service.noYoutubeVideo = true
        `when`(networkState.isOnline()).thenReturn(true)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieVideo(movieId).getOrAwaitValue() as Success
                //Assert
                assertNull(actual.dataResponse.data)
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testMovieVideoNoInternet() {
        // Arrange
        val movieId = 603L
        val tag = ApiTag.MOVIE_VIDEO_API_TAG
        `when`(networkState.isOnline()).thenReturn(false)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieVideo(movieId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, INTERNAL_ERROR)
                    assertEquals(tag, apiTag)
                    assertNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testMovieVideoUnauth() {
        // Arrange
        val movieId = 603L
        val tag = ApiTag.MOVIE_VIDEO_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = UNAUTHORIZED
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieVideo(movieId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, UNAUTHORIZED)
                    assertEquals(tag, apiTag)
                    assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testMovieVideoResourceNotFound() {
        // Arrange
        val movieId = 603L
        val tag = ApiTag.MOVIE_VIDEO_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = RESOURCE_NOT_FOUND
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieVideo(movieId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, RESOURCE_NOT_FOUND)
                    assertEquals(tag, apiTag)
                    assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testMovieCastSuccess() {
        // Arrange
        val expected = Gson().fromJson(movieCastResponse, CreditsApiModel::class.java)
        val movieId = 603L
        `when`(networkState.isOnline()).thenReturn(true)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieCast(movieId).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected.casts.size)
                verify(manager, single()).updateMovieDetails(any<List<CastModel>>(), anyLong())
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testMovieCastNoInternet() {
        // Arrange
        val movieId = 603L
        val tag = ApiTag.MOVIE_CREDITS_API_TAG
        `when`(networkState.isOnline()).thenReturn(false)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieCast(movieId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, INTERNAL_ERROR)
                    assertEquals(tag, apiTag)
                    assertNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testMovieCastUnauth() {
        // Arrange
        val movieId = 603L
        val tag = ApiTag.MOVIE_CREDITS_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = UNAUTHORIZED
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieCast(movieId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, UNAUTHORIZED)
                    assertEquals(tag, apiTag)
                    assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testMovieCastResourceNotFound() {
        // Arrange
        val movieId = 603L
        val tag = ApiTag.MOVIE_CREDITS_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = RESOURCE_NOT_FOUND
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getMovieCast(movieId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, RESOURCE_NOT_FOUND)
                    assertEquals(tag, apiTag)
                    assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testSimilarMovieSuccess() {
        // Arrange
        val similarMovies = Gson().fromJson(movieSimilarResponse, MovieListApiModel::class.java)
        val movieId = 603L
        val genre = getGenreMap()
        `when`(networkState.isOnline()).thenReturn(true)
        `when`(manager.getGenres()).thenReturn(genre)
        val expected = mutableListOf<ShowModelMinimal>()
        for (movie in similarMovies.results) {
            expected.add(getMovieMinimalApiModel(movie, genre))
        }

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getSimilarMovies(movieId).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected)
                verify(manager, single()).getGenres()
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testSimilarMovieNoInternet() {
        // Arrange
        val movieId = 603L
        val tag = ApiTag.MOVIE_SIMILAR_API_TAG
        `when`(networkState.isOnline()).thenReturn(false)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getSimilarMovies(movieId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, INTERNAL_ERROR)
                    assertEquals(apiTag, tag)
                    assertNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testSimilarMovieUnauth() {
        // Arrange
        val movieId = 603L
        val tag = ApiTag.MOVIE_SIMILAR_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = UNAUTHORIZED
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getSimilarMovies(movieId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, UNAUTHORIZED)
                    assertEquals(tag, apiTag)
                    assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testSimilarMovieResourceNotFound() {
        // Arrange
        val movieId = 603L
        val tag = ApiTag.MOVIE_SIMILAR_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = RESOURCE_NOT_FOUND
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getSimilarMovies(movieId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, RESOURCE_NOT_FOUND)
                    assertEquals(tag, apiTag)
                    assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testRecommendedMoviesSuccess() {
        // Arrange
        val similarMovies = Gson().fromJson(movieSimilarResponse, MovieListApiModel::class.java)
        val movieId = 603L
        val genre = getGenreMap()
        `when`(networkState.isOnline()).thenReturn(true)
        `when`(manager.getGenres()).thenReturn(genre)
        val expected = mutableListOf<ShowModelMinimal>()
        for (movie in similarMovies.results) {
            expected.add(getMovieMinimalApiModel(movie, genre))
        }

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getRecommendationMovies(movieId).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected)
                verify(manager, single()).getGenres()
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testRecommendedMovieNoInternet() {
        // Arrange
        val movieId = 603L
        val tag = ApiTag.MOVIE_RECOMMENDATION_API_TAG
        `when`(networkState.isOnline()).thenReturn(false)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getRecommendationMovies(movieId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, INTERNAL_ERROR)
                    assertEquals(apiTag, tag)
                    assertNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testRecommendedMovieUnauth() {
        // Arrange
        val movieId = 603L
        val tag = ApiTag.MOVIE_RECOMMENDATION_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = UNAUTHORIZED
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getRecommendationMovies(movieId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, UNAUTHORIZED)
                    assertEquals(tag, apiTag)
                    assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testRecommendedMovieResourceNotFound() {
        // Arrange
        val movieId = 603L
        val tag = ApiTag.MOVIE_RECOMMENDATION_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = RESOURCE_NOT_FOUND
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getRecommendationMovies(movieId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, RESOURCE_NOT_FOUND)
                    assertEquals(tag, apiTag)
                    assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
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