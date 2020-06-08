package com.sifat.slushflicks.repository.resource.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.sifat.slushflicks.api.StatusCode
import com.sifat.slushflicks.api.home.tv.TvServiceFake
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getGenreMap
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
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
import org.mockito.ArgumentMatchers
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TrendingTvListResourceTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: TrendingTvListResource
    private lateinit var tvService: TvServiceFake
    private lateinit var networkStateManager: NetworkStateManager
    private lateinit var dataManager: DataManager
    private val apiTag = "apiTag"
    private val collection = "collection"
    private val key = "key"

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        tvService = TvServiceFake(Gson())
        networkStateManager = mock(NetworkStateManager::class.java)
    }

    @Test
    fun testTvShowSuccessListFirstPageResponse() {
        initSut(1)
        `when`(networkStateManager.isOnline()).thenReturn(true)
        `when`(dataManager.getGenres()).thenReturn(getGenreMap())
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as DataState.Success<*>
                assertNotNull(actual.dataResponse)
                verify(dataManager, single()).getGenres()
                verify(dataManager, single()).softInsertTv(ArgumentMatchers.anyList())
                verify(dataManager, single()).insertNewTvCollection(
                    ArgumentMatchers.anyString(),
                    ArgumentMatchers.anyList()
                )
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testTvShowSuccessListLaterPageResponse() {
        initSut(2)
        `when`(networkStateManager.isOnline()).thenReturn(true)
        `when`(dataManager.getGenres()).thenReturn(getGenreMap())
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as DataState.Success<*>
                assertNotNull(actual.dataResponse)
                verify(dataManager, single()).getGenres()
                verify(dataManager, single()).softInsertTv(ArgumentMatchers.anyList())
                verify(dataManager, single()).addTvCollection(ArgumentMatchers.anyList())
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testTvShowErrorResponseNoInternet() {
        initSut(1)
        `when`(networkStateManager.isOnline()).thenReturn(false)
        Assertions.assertDoesNotThrow {
            val actual = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            assertNotNull(actual.dataResponse)
            actual.dataResponse.run {
                assertEquals(StatusCode.INTERNAL_ERROR, statusCode)
                assertEquals(this@TrendingTvListResourceTest.apiTag, apiTag)
                assertNull(errorMessage)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testErrorUnauthResponse() {
        initSut(1)
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.errorCode = StatusCode.UNAUTHORIZED
        Assertions.assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                assertEquals(StatusCode.UNAUTHORIZED, statusCode)
                assertNotNull(errorMessage)
                assertEquals(this@TrendingTvListResourceTest.apiTag, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testErrorNoResourceResponse() {
        initSut(1)
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.errorCode = StatusCode.RESOURCE_NOT_FOUND
        Assertions.assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                assertEquals(StatusCode.RESOURCE_NOT_FOUND, statusCode)
                assertNotNull(errorMessage)
                assertEquals(this@TrendingTvListResourceTest.apiTag, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    private fun initSut(page: Int) {
        sut = TrendingTvListResource(
            dataManager = dataManager,
            tvService = tvService,
            jobManager = mock(JobManager::class.java),
            networkStateManager = networkStateManager,
            requestModel = TvListNetworkResource.RequestModel(page, key, apiTag, collection),
            dispatcher = Dispatchers.Main
        )
    }
}