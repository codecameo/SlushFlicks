package com.sifat.slushflicks.repository.resource.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.gson.Gson
import com.sifat.slushflicks.api.StatusCode
import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.api.home.tv.TvServiceFake
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.repository.resource.impl.SimilarTvShowNetworkResource.RequestModel
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.getGenreMap
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SimilarTvShowNetworkResourceTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: SimilarTvShowNetworkResource
    private lateinit var tvService: TvServiceFake
    private lateinit var networkStateManager: NetworkStateManager
    private lateinit var dataManager: DataManager
    private val tvShowId = 18189L
    private val apiTag = "apiTag"
    private val label = "label"
    private val key = "key"

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        tvService = TvServiceFake(Gson())
        networkStateManager = mock(NetworkStateManager::class.java)
        sut = SimilarTvShowNetworkResource(
            dataManager = dataManager,
            tvService = tvService,
            jobManager = mock(JobManager::class.java),
            networkStateManager = networkStateManager,
            requestModel = RequestModel(key, apiTag, tvShowId, label),
            dispatcher = Dispatchers.Main
        )
    }

    @Test
    fun testSimilarTvShowSuccessResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        `when`(dataManager.getGenres()).thenReturn(getGenreMap())
        Assertions.assertDoesNotThrow {
            val actual = sut.asLiveData().getOrAwaitValue() as DataState.Success<*>
            assertNotNull(actual.dataResponse)
            verify(dataManager, single()).getGenres()
            verifyNoMoreInteractions(dataManager)
        }
    }

    @Test
    fun testSimilarTvShowErrorResponseNoInternet() {
        `when`(networkStateManager.isOnline()).thenReturn(false)
        Assertions.assertDoesNotThrow {
            val actual = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            assertNotNull(actual.dataResponse)
            actual.dataResponse.run {
                assertEquals(INTERNAL_ERROR, statusCode)
                assertEquals(this@SimilarTvShowNetworkResourceTest.apiTag, apiTag)
                assertNull(errorMessage)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testErrorUnauthResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.errorCode = StatusCode.UNAUTHORIZED
        Assertions.assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                assertEquals(StatusCode.UNAUTHORIZED, statusCode)
                assertNotNull(errorMessage)
                assertEquals(this@SimilarTvShowNetworkResourceTest.apiTag, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }

    @Test
    fun testErrorNoResourceResponse() {
        `when`(networkStateManager.isOnline()).thenReturn(true)
        tvService.errorCode = StatusCode.RESOURCE_NOT_FOUND
        Assertions.assertDoesNotThrow {
            val error = sut.asLiveData().getOrAwaitValue() as DataState.Error<*>
            error.dataResponse.run {
                assertEquals(StatusCode.RESOURCE_NOT_FOUND, statusCode)
                assertNotNull(errorMessage)
                assertEquals(this@SimilarTvShowNetworkResourceTest.apiTag, apiTag)
            }
            verifyZeroInteractions(dataManager)
        }
    }
}