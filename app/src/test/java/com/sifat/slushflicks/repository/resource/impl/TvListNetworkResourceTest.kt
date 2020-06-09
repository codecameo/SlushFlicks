package com.sifat.slushflicks.repository.resource.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.home.tv.TvServiceFake
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.repository.resource.impl.TvListNetworkResource.RequestModel
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getGenreMap
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TvListNetworkResourceTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: TvListNetworkResource
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
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as DataState.Success<*>
                assertNotNull(actual.dataResponse)
                verify(dataManager, single()).getGenres()
                verify(dataManager, single()).softInsertTv(anyList())
                verify(dataManager, single()).insertNewTvCollection(anyString(), anyList())
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testTvShowSuccessListLaterPageResponse() {
        initSut(2)
        `when`(networkStateManager.isOnline()).thenReturn(true)
        `when`(dataManager.getGenres()).thenReturn(getGenreMap())
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.asLiveData().getOrAwaitValue() as DataState.Success<*>
                assertNotNull(actual.dataResponse)
                verify(dataManager, single()).getGenres()
                verify(dataManager, single()).softInsertTv(anyList())
                verify(dataManager, single()).addTvCollection(anyList())
                verifyNoMoreInteractions(dataManager)
            }
        }
    }

    @Test
    fun testTvShowErrorResponseNoInternet() {
        initSut(1)
        `when`(networkStateManager.isOnline()).thenReturn(false)
        assertDoesNotThrow {
            val actual = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            assertNotNull(actual.dataResponse)
            actual.dataResponse.run {
                assertEquals(INTERNAL_ERROR, statusCode)
                assertEquals(this@TvListNetworkResourceTest.apiTag, apiTag)
                Assert.assertNull(errorMessage)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testErrorUnauthResponse() {
        initSut(1)
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.errorCode = UNAUTHORIZED
        assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                assertEquals(UNAUTHORIZED, statusCode)
                assertNotNull(errorMessage)
                assertEquals(this@TvListNetworkResourceTest.apiTag, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testErrorNoResourceResponse() {
        initSut(1)
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.errorCode = RESOURCE_NOT_FOUND
        assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                assertEquals(RESOURCE_NOT_FOUND, statusCode)
                assertNotNull(errorMessage)
                assertEquals(this@TvListNetworkResourceTest.apiTag, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    private fun initSut(page: Int) {
        sut = TvListNetworkResource(
            dataManager = dataManager,
            tvService = tvService,
            jobManager = mock(JobManager::class.java),
            networkStateManager = networkStateManager,
            requestModel = RequestModel(page, key, apiTag, collection),
            dispatcher = Dispatchers.Main
        )
    }
}