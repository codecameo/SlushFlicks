package com.sifat.slushflicks.repository.resource.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiTag.Companion.TV_VIDEO_API_TAG
import com.sifat.slushflicks.api.StatusCode
import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.api.home.tv.TvServiceFake
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.repository.resource.impl.TvVideoNetworkResource.RequestModel
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.utils.any
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvVideoNetworkResourceTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: TvVideoNetworkResource
    private lateinit var tvService: TvServiceFake
    private lateinit var jobManager: JobManager
    private lateinit var networkStateManager: NetworkStateManager
    private lateinit var dataManager: DataManager
    private val tvShowId = 100L
    private val seasonNumber = 1

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        tvService = TvServiceFake(Gson())
        networkStateManager = mock(NetworkStateManager::class.java)
        jobManager = mock(JobManager::class.java)
        sut = TvVideoNetworkResource(
            dataManager = dataManager,
            tvService = tvService,
            jobManager = jobManager,
            networkStateManager = networkStateManager,
            requestModel = RequestModel("key", tvShowId, seasonNumber),
            dispatcher = Dispatchers.Main
        )
    }

    @Test
    fun testVideoSuccessResponseWithYoutubeTrailer() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as Success<*>
                assertNotNull(actual.dataResponse.data)
                verify(dataManager, times(1)).updateTvDetails(
                    any<VideoApiModel>(),
                    anyLong()
                )
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testVideoSuccessResponseWithNoYoutubeTrailer() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.noYoutubeVideo = true
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as Success<*>
                assertNull(actual.dataResponse.data)
                verifyZeroInteractions(dataManager)
            }
        }
    }

    @Test
    fun testVideoErrorNoInternetResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(false)
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
                assertEquals(INTERNAL_ERROR, error.dataResponse.statusCode)
                verifyZeroInteractions(dataManager)
            }
        }
    }

    @Test
    fun testVideoErrorUnauthResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.errorCode = StatusCode.UNAUTHORIZED
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
                error.dataResponse?.run {
                    assertEquals(StatusCode.UNAUTHORIZED, statusCode)
                    assertNotNull(errorMessage)
                    assertNotNull(TV_VIDEO_API_TAG, apiTag)
                }
                verifyZeroInteractions(dataManager)
            }
        }
    }

    @Test
    fun testVideoErrorNoResourceResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.errorCode = RESOURCE_NOT_FOUND
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
                error.dataResponse?.run {
                    assertEquals(RESOURCE_NOT_FOUND, statusCode)
                    assertNotNull(errorMessage)
                    assertEquals(TV_VIDEO_API_TAG, apiTag)
                }
                verifyZeroInteractions(dataManager)
            }
        }
    }
}