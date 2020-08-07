package com.sifat.slushflicks.repository.tv.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiTag
import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.api.StatusCode.Companion.RESOURCE_NOT_FOUND
import com.sifat.slushflicks.api.StatusCode.Companion.UNAUTHORIZED
import com.sifat.slushflicks.api.home.tv.TvServiceFake
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.util.getOrAwaitValue
import com.sifat.slushflicks.utils.any
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.api.tvShowInvalidPage
import com.sifat.slushflicks.utils.api.tvShowPage1
import com.sifat.slushflicks.utils.api.tvShowPage2
import com.sifat.slushflicks.utils.getFakeDataSource
import com.sifat.slushflicks.utils.getShowList
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TopRatedTvRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: TopRatedTvRepositoryImpl

    private lateinit var manager: DataManager
    private lateinit var service: TvServiceFake
    private lateinit var apiKey: String
    private lateinit var networkState: NetworkStateManager
    private lateinit var jobManager: JobManager
    private val boundaryCallback: PagedList.BoundaryCallback<ShowModelMinimal> =
        object : PagedList.BoundaryCallback<ShowModelMinimal>() {}

    @Before
    fun setup() {
        manager = mock(DataManager::class.java)
        service = TvServiceFake(Gson())
        jobManager = mock(JobManager::class.java)
        networkState = mock(NetworkStateManager::class.java)
        apiKey = "apiKey"
        sut = TopRatedTvRepositoryImpl(
            dataManager = manager,
            tvService = service,
            apiKey = apiKey,
            jobManager = jobManager,
            networkStateManager = networkState,
            dispatcher = Main
        )
    }

    @Test
    fun testCollectionFirstPage() {
        // Arrange
        val list = Gson().fromJson(tvShowPage1, TvListApiModel::class.java)
        val expected = list.results.size
        `when`(networkState.isOnline()).thenReturn(true)
        //Act
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.getTvList(1).getOrAwaitValue() as Success
                //Assert
                assertEquals(expected, actual.dataResponse.data)
                verify(manager, single()).softInsertTv(any())
                verify(manager, single()).insertNewTvCollection(any(), any())
                verify(manager, single()).getGenres()
                verify(networkState, single()).isOnline()
                verify(jobManager, single()).addJob(any(), any())
                verifyNoMoreInteractions(networkState)
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(jobManager)
            }
        }
    }

    @Test
    fun testCollectionSecondPage() {
        // Arrange
        val list = Gson().fromJson(tvShowPage2, TvListApiModel::class.java)
        val expected = list.results.size
        `when`(networkState.isOnline()).thenReturn(true)
        //Act
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.getTvList(2).getOrAwaitValue() as Success
                //Assert
                assertEquals(expected, actual.dataResponse.data)
                verify(manager, single()).softInsertTv(any())
                verify(manager, single()).addTvCollection(any())
                verify(manager, single()).getGenres()
                verify(networkState, single()).isOnline()
                verify(jobManager, single()).addJob(any(), any())
                verifyNoMoreInteractions(networkState)
                verifyNoMoreInteractions(manager)
                verifyNoMoreInteractions(jobManager)
            }
        }
    }

    @Test
    fun testCollectionInvalidPage() {
        // Arrange
        val list = Gson().fromJson(tvShowInvalidPage, TvListApiModel::class.java)
        val expected = list.results.size
        `when`(networkState.isOnline()).thenReturn(true)
        //Act
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.getTvList(3).getOrAwaitValue() as Success
                //Assert
                assertEquals(expected, actual.dataResponse.data)
                verify(networkState, single()).isOnline()
                verify(jobManager, single()).addJob(any(), any())
                verifyNoMoreInteractions(networkState)
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(jobManager)
            }
        }
    }

    @Test
    fun testTvShowCastNoInternet() {
        // Arrange
        val tag = ApiTag.TOP_RATED_TV_API_TAG
        `when`(networkState.isOnline()).thenReturn(false)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvList(1).getOrAwaitValue() as Error
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
    fun testTvShowCastUnauth() {
        // Arrange
        val tag = ApiTag.TOP_RATED_TV_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = UNAUTHORIZED
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvList(1).getOrAwaitValue() as Error
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
    fun testTvShowCastResourceNotFound() {
        // Arrange
        val tag = ApiTag.TOP_RATED_TV_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = RESOURCE_NOT_FOUND
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvList(1).getOrAwaitValue() as Error
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
    fun testTvShowList() {
        // Arrange
        val expected = getShowList()
        val dataSource = getFakeDataSource<Int, ShowModelMinimal>(expected)
        `when`(manager.getPagingTvShows(anyString())).thenReturn(dataSource)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getPagingTvList(boundaryCallback).getOrAwaitValue() as Success

                //Assert
                assertEquals(expected[2], actual.dataResponse.data?.get(2))
                verify(manager, single()).getPagingTvShows(anyString())
                verifyNoMoreInteractions(manager)
                verifyZeroInteractions(networkState)
                verifyZeroInteractions(jobManager)
            }
        }
    }
}