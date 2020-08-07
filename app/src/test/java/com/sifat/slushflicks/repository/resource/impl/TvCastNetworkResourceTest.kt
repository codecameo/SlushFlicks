package com.sifat.slushflicks.repository.resource.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiTag.Companion.TV_CREDITS_API_TAG
import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.home.tv.TvServiceFake
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
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentCaptor
import org.mockito.ArgumentCaptor.forClass
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvCastNetworkResourceTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: TvCastNetworkResource
    private lateinit var tvService: TvServiceFake
    private lateinit var jobManager: JobManager
    private lateinit var networkStateManager: NetworkStateManager
    private lateinit var dataManager: DataManager
    private val tvShowId = 100L

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        tvService = TvServiceFake(Gson())
        networkStateManager = mock(NetworkStateManager::class.java)
        jobManager = mock(JobManager::class.java)
        sut = TvCastNetworkResource(
            dataManager = dataManager,
            tvService = tvService,
            jobManager = jobManager,
            networkStateManager = networkStateManager,
            requestModel = TvCastNetworkResource.RequestModel("key", tvShowId),
            dispatcher = Dispatchers.Main
        )
    }

    @Test
    fun testCastSuccessResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                sut.asLiveData().getOrAwaitValue() as DataState.Success<*>
                verify(dataManager, single()).updateTvDetails(anyList(), anyLong())
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testCastSuccessResponseCheckMaxListCount() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as DataState.Success<*>
                assertEquals(16, actual.dataResponse.data)
                val listClass: Class<List<CastModel>> = List::class.java as Class<List<CastModel>>
                val argument: ArgumentCaptor<List<CastModel>> = forClass(listClass)
                verify(dataManager, single()).updateTvDetails(
                    capture(argument), anyLong()
                )
                assertEquals(15, argument.value.size)
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testCastErrorNoInternetResponse() {
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
    fun testCastErrorUnauthResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.errorCode = UNAUTHORIZED
        assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                assertEquals(UNAUTHORIZED, statusCode)
                assertNotNull(errorMessage)
                assertEquals(TV_CREDITS_API_TAG, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testCastErrorNoResourceResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.errorCode = RESOURCE_NOT_FOUND
        assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                assertEquals(RESOURCE_NOT_FOUND, statusCode)
                assertNotNull(errorMessage)
                assertEquals(TV_CREDITS_API_TAG, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }
}