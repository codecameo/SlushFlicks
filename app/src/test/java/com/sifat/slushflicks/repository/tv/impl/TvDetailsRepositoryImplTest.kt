package com.sifat.slushflicks.repository.tv.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiTag.Companion.TV_CREDITS_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.TV_SHOW_RECOMMENDATION_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.TV_SHOW_SIMILAR_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.TV_VIDEO_API_TAG
import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.details.model.CreditsApiModel
import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.api.details.model.VideoListApiModel
import com.sifat.slushflicks.api.home.tv.TvServiceFake
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.CastModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.repository.tv.TvDetailsRepository
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.helper.getTvShowMinimalApiModel
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.utils.any
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.api.tvCastResponse
import com.sifat.slushflicks.utils.api.tvSimilarResponse
import com.sifat.slushflicks.utils.api.tvVideoResponse
import com.sifat.slushflicks.utils.getGenreMap
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
import com.sifat.slushflicks.utils.tvDetailsResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
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
class TvDetailsRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    internal lateinit var sut: TvDetailsRepository
    private lateinit var manager: DataManager
    private lateinit var service: TvServiceFake
    private lateinit var apiKey: String
    private lateinit var networkState: NetworkStateManager
    private lateinit var jobManager: JobManager

    @Before
    fun setup() {
        manager = mock(DataManager::class.java)
        service = TvServiceFake(Gson())
        jobManager = mock(JobManager::class.java)
        networkState = mock(NetworkStateManager::class.java)
        apiKey = "apiKey"
        sut = TvDetailsRepositoryImpl(
            dataManager = manager,
            tvService = service,
            apiKey = apiKey,
            jobManager = jobManager,
            networkStateManager = networkState,
            dispatcher = Dispatchers.Main
        )
    }

    @Test
    fun testTvDetailsSuccess() {
        // Arrange
        val expected = Gson().fromJson(tvDetailsResponse, TvModel::class.java)
        val live = MutableLiveData<TvModel>()
        live.value = expected
        `when`(manager.getTvShowDetails(anyLong())).thenReturn(live)
        `when`(networkState.isOnline()).thenReturn(true)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual =
                    sut.getTvShowDetails(expected.id).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected)
                verify(manager, single()).getTvShowDetails(expected.id)
                verify(manager, single()).updateTvDetails(any())
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testTvDetailsNoNetwork() {
        // Arrange
        val expected = Gson().fromJson(tvDetailsResponse, TvModel::class.java)
        val live = MutableLiveData<TvModel>()
        live.value = expected
        `when`(manager.getTvShowDetails(anyLong())).thenReturn(live)
        `when`(networkState.isOnline()).thenReturn(false)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvShowDetails(expected.id).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected)
                verify(manager, single()).getTvShowDetails(expected.id)
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testTvDetailsErrorUnauth() {
        // Arrange
        service.errorCode = UNAUTHORIZED
        val expected = Gson().fromJson(tvDetailsResponse, TvModel::class.java)
        val live = MutableLiveData<TvModel>()
        live.value = expected
        `when`(manager.getTvShowDetails(anyLong())).thenReturn(live)
        `when`(networkState.isOnline()).thenReturn(true)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvShowDetails(expected.id).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected)
                verify(manager, single()).getTvShowDetails(expected.id)
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testTvDetailsErrorResNotFound() {
        // Arrange
        service.errorCode = RESOURCE_NOT_FOUND
        val expected = Gson().fromJson(tvDetailsResponse, TvModel::class.java)
        val live = MutableLiveData<TvModel>()
        live.value = expected
        `when`(manager.getTvShowDetails(anyLong())).thenReturn(live)
        `when`(networkState.isOnline()).thenReturn(true)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual =
                    sut.getTvShowDetails(expected.id).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected)
                verify(manager, single()).getTvShowDetails(expected.id)
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testTvVideoSuccess() {
        // Arrange
        val videoList = Gson().fromJson(tvVideoResponse, VideoListApiModel::class.java)
        val expected = videoList.results[0].key
        val tvShowId = 603L
        val sessionNumber = 3
        `when`(networkState.isOnline()).thenReturn(true)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual =
                    sut.getTvShowVideo(tvShowId, sessionNumber).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected)
                verify(manager, single()).updateTvDetails(
                    any<VideoApiModel>(),
                    anyLong()
                )
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testTvNoVideoSuccess() {
        // Arrange
        val tvShowId = 603L
        val sessionNumber = 3
        service.noYoutubeVideo = true
        `when`(networkState.isOnline()).thenReturn(true)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual =
                    sut.getTvShowVideo(tvShowId, sessionNumber).getOrAwaitValue() as Success
                //Assert
                assertNull(actual.dataResponse.data)
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testTvVideoNoInternet() {
        // Arrange
        val tvShowId = 603L
        val sessionNumber = 3
        val tag = TV_VIDEO_API_TAG
        `when`(networkState.isOnline()).thenReturn(false)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvShowVideo(tvShowId, sessionNumber).getOrAwaitValue() as Error
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
    fun testTvVideoUnauth() {
        // Arrange
        val tvShowId = 603L
        val sessionNumber = 3
        val tag = TV_VIDEO_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = UNAUTHORIZED
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvShowVideo(tvShowId, sessionNumber).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, UNAUTHORIZED)
                    assertEquals(tag, apiTag)
                    kotlin.test.assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testTvVideoResourceNotFound() {
        // Arrange
        val tvShowId = 603L
        val sessionNumber = 3
        val tag = TV_VIDEO_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = RESOURCE_NOT_FOUND
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvShowVideo(tvShowId, sessionNumber).getOrAwaitValue() as Error
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
    fun testTvCastSuccess() {
        // Arrange
        val expected = Gson().fromJson(tvCastResponse, CreditsApiModel::class.java)
        val tvId = 603L
        `when`(networkState.isOnline()).thenReturn(true)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvShowCast(tvId).getOrAwaitValue() as Success
                //Assert
                assertEquals(actual.dataResponse.data, expected.casts.size)
                verify(manager, single()).updateTvDetails(
                    any<List<CastModel>>(),
                    anyLong()
                )
                verify(networkState, single()).isOnline()
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testTvCastNoInternet() {
        // Arrange
        val tvId = 603L
        val tag = TV_CREDITS_API_TAG
        `when`(networkState.isOnline()).thenReturn(false)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvShowCast(tvId).getOrAwaitValue() as Error
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
    fun testTvCastUnauth() {
        // Arrange
        val tvId = 603L
        val tag = TV_CREDITS_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = UNAUTHORIZED
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvShowCast(tvId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, UNAUTHORIZED)
                    assertEquals(tag, apiTag)
                    kotlin.test.assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testTvCastResourceNotFound() {
        // Arrange
        val tvId = 603L
        val tag = TV_CREDITS_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = RESOURCE_NOT_FOUND
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvShowCast(tvId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, RESOURCE_NOT_FOUND)
                    assertEquals(tag, apiTag)
                    kotlin.test.assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testSimilarTvSuccess() {
        // Arrange
        val similarTvs = Gson().fromJson(tvSimilarResponse, TvListApiModel::class.java)
        val tvId = 603L
        val genre = getGenreMap()
        `when`(networkState.isOnline()).thenReturn(true)
        `when`(manager.getGenres()).thenReturn(genre)
        val expected = mutableListOf<ShowModelMinimal>()
        for (tv in similarTvs.results) {
            expected.add(getTvShowMinimalApiModel(tv, genre))
        }

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual =
                    sut.getSimilarTvShows(tvId).getOrAwaitValue() as Success
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
    fun testSimilarTvNoInternet() {
        // Arrange
        val tvId = 603L
        val tag = TV_SHOW_SIMILAR_API_TAG
        `when`(networkState.isOnline()).thenReturn(false)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getSimilarTvShows(tvId).getOrAwaitValue() as Error
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
    fun testSimilarTvUnauth() {
        // Arrange
        val tvId = 603L
        val tag = TV_SHOW_SIMILAR_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = UNAUTHORIZED
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getSimilarTvShows(tvId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, UNAUTHORIZED)
                    assertEquals(tag, apiTag)
                    kotlin.test.assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testSimilarTvResourceNotFound() {
        // Arrange
        val tvId = 603L
        val tag = TV_SHOW_SIMILAR_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = RESOURCE_NOT_FOUND
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getSimilarTvShows(tvId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, RESOURCE_NOT_FOUND)
                    assertEquals(tag, apiTag)
                    kotlin.test.assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testRecommendedTvsSuccess() {
        // Arrange
        val similarTvs = Gson().fromJson(tvSimilarResponse, TvListApiModel::class.java)
        val tvId = 603L
        val genre = getGenreMap()
        `when`(networkState.isOnline()).thenReturn(true)
        `when`(manager.getGenres()).thenReturn(genre)
        val expected = mutableListOf<ShowModelMinimal>()
        for (tv in similarTvs.results) {
            expected.add(getTvShowMinimalApiModel(tv, genre))
        }

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getRecommendationTvShows(tvId).getOrAwaitValue() as Success
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
    fun testRecommendedTvNoInternet() {
        // Arrange
        val tvId = 603L
        val tag = TV_SHOW_RECOMMENDATION_API_TAG
        `when`(networkState.isOnline()).thenReturn(false)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual =
                    sut.getRecommendationTvShows(tvId).getOrAwaitValue() as Error
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
    fun testRecommendedTvUnauth() {
        // Arrange
        val tvId = 603L
        val tag = TV_SHOW_RECOMMENDATION_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = UNAUTHORIZED
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual =
                    sut.getRecommendationTvShows(tvId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, UNAUTHORIZED)
                    assertEquals(tag, apiTag)
                    kotlin.test.assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testRecommendedTvResourceNotFound() {
        // Arrange
        val tvId = 603L
        val tag = TV_SHOW_RECOMMENDATION_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = RESOURCE_NOT_FOUND
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual =
                    sut.getRecommendationTvShows(tvId).getOrAwaitValue() as Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, RESOURCE_NOT_FOUND)
                    assertEquals(tag, apiTag)
                    kotlin.test.assertNotNull(errorMessage)
                }
                verify(networkState, single()).isOnline()
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(networkState)
            }
        }
    }

    @Test
    fun testUpdateRecentTv() {
        // Arrange
        val tvId = 603L

        mainCoroutineDispatcher.runBlockingTest {
            //Act
            sut.updateRecentTvShow(tvId)

            //Assert
            verify(manager, single()).insertNewTvCollection(any())
            verifyZeroInteractions(networkState)
            verifyNoMoreInteractions(manager)
        }
    }

    @Test
    fun testCancelAllJobs() {

        // Arrange

        //Act
        sut.cancelAllJobs()

        //Assert
        verify(jobManager, single()).cancelActiveJobs()
        verifyNoMoreInteractions(jobManager)
        verifyZeroInteractions(manager)
        verifyZeroInteractions(networkState)
    }

    @Test
    fun getReviews() {
        // Arrange
        //Act
        //Assert
    }
}
