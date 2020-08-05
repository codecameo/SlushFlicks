package com.sifat.slushflicks.repository.tv.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList.BoundaryCallback
import com.google.gson.Gson
import com.sifat.slushflicks.api.ApiTag
import com.sifat.slushflicks.api.StatusCode
import com.sifat.slushflicks.api.home.tv.TvServiceFake
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.helper.JobManager
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.util.getOrAwaitValue
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.api.tvShowInvalidPage
import com.sifat.slushflicks.utils.api.tvShowPage1
import com.sifat.slushflicks.utils.api.tvShowPage2
import com.sifat.slushflicks.utils.getFakeMovieDataSource
import com.sifat.slushflicks.utils.getShowList
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.Dispatchers.Main
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
class PopularTvRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: PopularTvRepositoryImpl

    private lateinit var manager: DataManager
    private lateinit var service: TvServiceFake
    private lateinit var apiKey: String
    private lateinit var networkState: NetworkStateManager
    private lateinit var jobManager: JobManager
    private val boundaryCallback: BoundaryCallback<ShowModelMinimal> =
        object : BoundaryCallback<ShowModelMinimal>() {}

    @Before
    fun setup() {
        manager = mock(DataManager::class.java)
        service = TvServiceFake(Gson())
        jobManager = mock(JobManager::class.java)
        networkState = mock(NetworkStateManager::class.java)
        apiKey = "apiKey"
        sut = PopularTvRepositoryImpl(
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
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.getTvList(1).getOrAwaitValue() as Success
                //Assert
                assertEquals(expected, actual.dataResponse.data)
                verify(manager, single()).softInsertTv(com.sifat.slushflicks.utils.any())
                verify(manager, single()).insertNewTvCollection(
                    com.sifat.slushflicks.utils.any(),
                    com.sifat.slushflicks.utils.any()
                )
                verify(manager, single()).getGenres()
                verify(networkState, single()).isOnline()
                verify(jobManager, single()).addJob(
                    com.sifat.slushflicks.utils.any(),
                    com.sifat.slushflicks.utils.any()
                )
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
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.getTvList(2).getOrAwaitValue() as Success
                //Assert
                assertEquals(expected, actual.dataResponse.data)
                verify(manager, single()).softInsertTv(com.sifat.slushflicks.utils.any())
                verify(manager, single()).addTvCollection(com.sifat.slushflicks.utils.any())
                verify(manager, single()).getGenres()
                verify(networkState, single()).isOnline()
                verify(jobManager, single()).addJob(
                    com.sifat.slushflicks.utils.any(),
                    com.sifat.slushflicks.utils.any()
                )
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
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.getTvList(3).getOrAwaitValue() as Success
                //Assert
                assertEquals(expected, actual.dataResponse.data)
                verify(networkState, single()).isOnline()
                verify(jobManager, single()).addJob(
                    com.sifat.slushflicks.utils.any(),
                    com.sifat.slushflicks.utils.any()
                )
                verifyNoMoreInteractions(networkState)
                verifyZeroInteractions(manager)
                verifyNoMoreInteractions(jobManager)
            }
        }
    }

    @Test
    fun testTvShowCastNoInternet() {
        // Arrange
        val tag = ApiTag.POPULAR_TV_API_TAG
        `when`(networkState.isOnline()).thenReturn(false)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvList(1).getOrAwaitValue() as DataState.Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, StatusCode.INTERNAL_ERROR)
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
        val tag = ApiTag.POPULAR_TV_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = StatusCode.UNAUTHORIZED
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvList(1).getOrAwaitValue() as DataState.Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, StatusCode.UNAUTHORIZED)
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
        val tag = ApiTag.POPULAR_TV_API_TAG
        `when`(networkState.isOnline()).thenReturn(true)
        service.errorCode = StatusCode.RESOURCE_NOT_FOUND
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getTvList(1).getOrAwaitValue() as DataState.Error
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, StatusCode.RESOURCE_NOT_FOUND)
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
        val dataSource = getFakeMovieDataSource<Int, ShowModelMinimal>(expected)
        `when`(manager.getPagingTvShows(ArgumentMatchers.anyString())).thenReturn(dataSource)

        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getPagingTvList(boundaryCallback).getOrAwaitValue() as Success

                //Assert
                assertEquals(expected[2], actual.dataResponse.data?.get(2))
                verify(manager, single()).getPagingTvShows(ArgumentMatchers.anyString())
                verifyNoMoreInteractions(manager)
                verifyZeroInteractions(networkState)
                verifyZeroInteractions(jobManager)
            }
        }
    }
}