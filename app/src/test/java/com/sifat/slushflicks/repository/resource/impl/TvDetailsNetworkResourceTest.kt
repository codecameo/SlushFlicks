package com.sifat.slushflicks.repository.resource.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiTag.Companion.TV_SHOW_API_TAG
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.home.tv.TvServiceFake
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.repository.resource.impl.TvDetailsNetworkResource.RequestModel
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.helper.getTvDetails
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.utils.any
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.getTvDiffDetailsTestModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
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
class TvDetailsNetworkResourceTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: TvDetailsNetworkResource
    private lateinit var tvService: TvServiceFake
    private lateinit var networkStateManager: NetworkStateManager
    private lateinit var dataManager: DataManager
    private val tvShowId = 18189L

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        tvService = TvServiceFake(Gson())
        networkStateManager = mock(NetworkStateManager::class.java)
        sut = TvDetailsNetworkResource(
            dataManager = dataManager,
            tvService = tvService,
            jobManager = mock(JobManager::class.java),
            networkStateManager = networkStateManager,
            request = RequestModel("key", tvShowId),
            dispatcher = Dispatchers.Main
        )
    }

    @Test
    fun testTvDetailsSuccessResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        val tvModel = getTvDetails(getTvDiffDetailsTestModel())
        val live = MutableLiveData<TvModel>()
        live.value = tvModel
        `when`(dataManager.getTvShowDetails(tvShowId)).thenReturn(live)
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                sut.asLiveData().getOrAwaitValue() as Success<*>
                verify(dataManager, times(1)).getTvShowDetails(anyLong())
                verify(dataManager, times(1)).updateTvDetails(any())
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testTvDetailsSuccessResponseNoInternet() {
        `when`(networkStateManager.isOnline()).thenReturn(false)
        val tvModel = getTvDetails(getTvDiffDetailsTestModel())
        val live = MutableLiveData<TvModel>()
        live.value = tvModel
        `when`(dataManager.getTvShowDetails(tvShowId)).thenReturn(live)
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as Success<TvModel>
                assertEquals(tvModel, actual.dataResponse.data)
                verify(dataManager, times(1)).getTvShowDetails(anyLong())
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testTvDetailsErrorResponseNoInternet() {
        `when`(networkStateManager.isOnline()).thenReturn(false)
        val live = MutableLiveData<TvModel>()
        live.value = null
        `when`(dataManager.getTvShowDetails(tvShowId)).thenReturn(live)
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as Success<TvModel>
                assertNull(actual.dataResponse.data)
                verify(dataManager, times(1)).getTvShowDetails(anyLong())
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testTvDetailsSuccessResponseUnauth() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.errorCode = UNAUTHORIZED
        val tvModel = getTvDetails(getTvDiffDetailsTestModel())
        val live = MutableLiveData<TvModel>()
        live.value = tvModel
        `when`(dataManager.getTvShowDetails(tvShowId)).thenReturn(live)
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val sutLive = sut.asLiveData()
                val initVal = sutLive.getOrAwaitValue() as Success<TvModel>
                assertEquals(tvModel, initVal.dataResponse.data)
                verify(dataManager, times(1)).getTvShowDetails(anyLong())
                val laterVal = sutLive.getOrAwaitValue() as Error<TvModel>
                laterVal.dataResponse.run {
                    assertEquals(statusCode, UNAUTHORIZED)
                    assertNotNull(errorMessage)
                    assertEquals(TV_SHOW_API_TAG, apiTag)
                }
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testTvDetailsSuccessResponseNoResource() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.errorCode = RESOURCE_NOT_FOUND
        val tvModel = getTvDetails(getTvDiffDetailsTestModel())
        val live = MutableLiveData<TvModel>()
        live.value = tvModel
        `when`(dataManager.getTvShowDetails(tvShowId)).thenReturn(live)
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val sutLive = sut.asLiveData()
                val initVal = sutLive.getOrAwaitValue() as Success<TvModel>
                assertEquals(tvModel, initVal.dataResponse.data)
                verify(dataManager, times(1)).getTvShowDetails(anyLong())
                val laterVal = sutLive.getOrAwaitValue() as Error<TvModel>
                laterVal.dataResponse.run {
                    assertEquals(statusCode, RESOURCE_NOT_FOUND)
                    assertNotNull(errorMessage)
                    assertEquals(TV_SHOW_API_TAG, apiTag)
                }
                verifyNoMoreInteractions(dataManager)
            }
        }
    }
}