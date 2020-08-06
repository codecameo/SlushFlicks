package com.sifat.slushflicks.repository.search.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.PagedList
import com.google.gson.Gson
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.api.home.search.SearchServiceFake
import com.sifat.slushflicks.api.home.search.SearchServiceFake.Companion.invalidSearchKey
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.repository.search.SearchRepository
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.helper.getMovieMinimalApiModel
import com.sifat.slushflicks.ui.helper.getTvShowMinimalApiModel
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.util.getOrAwaitValue
import com.sifat.slushflicks.utils.api.moviePage1
import com.sifat.slushflicks.utils.api.tvShowPage1
import com.sifat.slushflicks.utils.getFakeDataSource
import com.sifat.slushflicks.utils.getGenreMap
import com.sifat.slushflicks.utils.getShowList
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    internal lateinit var sut: SearchRepository
    private lateinit var manager: DataManager
    private lateinit var service: SearchServiceFake
    private lateinit var apiKey: String
    private val boundaryCallback: PagedList.BoundaryCallback<ShowModelMinimal> =
        object : PagedList.BoundaryCallback<ShowModelMinimal>() {}

    @Before
    fun setup() {
        service = SearchServiceFake(Gson())
        manager = mock(DataManager::class.java)
        apiKey = "apiKey"
        sut = SearchRepositoryImpl(
            apiKey = apiKey,
            dataManager = manager,
            searchService = service
        )
    }

    @Test
    fun testSearchTvShowsWithValidKey() {
        // Arrange
        val apiModel = Gson().fromJson(tvShowPage1, TvListApiModel::class.java)
        val expected = mutableListOf<ShowModelMinimal>()
        for (model in apiModel.results) {
            expected.add(getTvShowMinimalApiModel(model, getGenreMap()))
        }
        `when`(manager.getGenres()).thenReturn(getGenreMap())
        //Act
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.searchTvShows("key", boundaryCallback).getOrAwaitValue() as Success
                //Assert
                actual.dataResponse.data?.let {
                    for (idx in it.indices) {
                        assertEquals(expected[idx], it[idx])
                    }
                }
                verify(manager, times(expected.size)).getGenres()
                verifyNoMoreInteractions(manager)
            }
        }
    }

    @Test
    fun testSearchTvShowsWithInvalidKey() {
        // Arrange

        //Act
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.searchTvShows(invalidSearchKey, boundaryCallback)
                    .getOrAwaitValue() as Success
                //Assert
                assertEquals(true, actual.dataResponse.data.isNullOrEmpty())
                verifyZeroInteractions(manager)
            }
        }
    }

    @Test
    fun testSearchMoviesWithValidKey() {
        // Arrange
        val apiModel = Gson().fromJson(moviePage1, MovieListApiModel::class.java)
        val expected = mutableListOf<ShowModelMinimal>()
        for (model in apiModel.results) {
            expected.add(getMovieMinimalApiModel(model, getGenreMap()))
        }
        `when`(manager.getGenres()).thenReturn(getGenreMap())
        //Act
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.searchMovies("key", boundaryCallback).getOrAwaitValue() as Success
                //Assert
                actual.dataResponse.data?.let {
                    for (idx in it.indices) {
                        assertEquals(expected[idx], it[idx])
                    }
                }
                verify(manager, times(expected.size)).getGenres()
                verifyNoMoreInteractions(manager)
            }
        }
    }

    @Test
    fun testSearchMoviesWithInvalidKey() {
        // Arrange

        //Act
        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                val actual = sut.searchMovies(invalidSearchKey, boundaryCallback)
                    .getOrAwaitValue() as Success
                //Assert
                assertEquals(true, actual.dataResponse.data.isNullOrEmpty())
                verifyZeroInteractions(manager)
            }
        }
    }

    @Test
    fun testRecentMovieList() {
        // Arrange
        val expected = getShowList()
        val dataSource = getFakeDataSource<Int, ShowModelMinimal>(expected)
        `when`(manager.getPagingMovies(anyString())).thenReturn(dataSource)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getRecentMovieList(boundaryCallback).getOrAwaitValue() as Success

                //Assert
                Assert.assertEquals(expected[2], actual.dataResponse.data?.get(2))
                verify(manager, single()).getPagingMovies(anyString())
                verifyNoMoreInteractions(manager)
            }
        }
    }

    @Test
    fun testRecentTvShowList() {
        // Arrange
        val expected = getShowList()
        val dataSource = getFakeDataSource<Int, ShowModelMinimal>(expected)
        `when`(manager.getPagingTvShows(anyString())).thenReturn(dataSource)

        assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                //Act
                val actual = sut.getRecentTvShowList(boundaryCallback).getOrAwaitValue() as Success

                //Assert
                Assert.assertEquals(expected[2], actual.dataResponse.data?.get(2))
                verify(manager, single()).getPagingTvShows(anyString())
                verifyNoMoreInteractions(manager)
            }
        }
    }
}