package com.sifat.slushflicks.repository.genre.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.sifat.slushflicks.api.StatusCode.Companion.INTERNAL_ERROR
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.repository.resource.impl.GenreNetworkResource
import com.sifat.slushflicks.repository.resource.impl.MovieGenreNetworkResource
import com.sifat.slushflicks.repository.resource.impl.TvGenreNetworkResource
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataErrorResponse
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataState.Error
import com.sifat.slushflicks.ui.state.DataState.Success
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.getGenreList
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import kotlin.test.assertEquals
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GenreRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: GenreRepositoryImpl
    private lateinit var dataManager: DataManager

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        sut = GenreRepositoryImpl(dataManager)
    }

    @Test
    fun testSettingGenreList() {
        val resource = mock(GenreNetworkResource::class.java)
        sut.genreNetworkResource = resource
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                // Arrange
                val live = MutableLiveData<DataState<List<GenreModel>>>()
                val list = getGenreList()
                live.value = Success(DataSuccessResponse(data = list))
                `when`(resource.asLiveData()).thenReturn(live)
                //Act
                val actual = sut.setGenreList().getOrAwaitValue() as Success<*>
                //Assert
                assertEquals(actual.dataResponse.data, list)
                verify(resource, single()).asLiveData()
                verify(dataManager, single()).initGenres(anyList())
                verifyNoMoreInteractions(dataManager)
                verifyNoMoreInteractions(resource)
            }
        }
    }

    @Test
    fun testSettingNullGenreList() {
        val resource = mock(GenreNetworkResource::class.java)
        sut.genreNetworkResource = resource
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                // Arrange
                val live = MutableLiveData<DataState<List<GenreModel>>>()
                live.value = Success(DataSuccessResponse<List<GenreModel>>(data = null))
                `when`(resource.asLiveData()).thenReturn(live)
                //Act
                val actual = sut.setGenreList().getOrAwaitValue() as Success<*>
                //Assert
                assertNull(actual.dataResponse.data)
                verify(resource, single()).asLiveData()
                verify(dataManager, single()).initGenres(null)
                verifyZeroInteractions(dataManager)
                verifyNoMoreInteractions(resource)
            }
        }
    }

    @Test
    fun testSettingGenreListErrorState() {
        val resource = mock(GenreNetworkResource::class.java)
        sut.genreNetworkResource = resource
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                // Arrange
                val live = MutableLiveData<DataState<List<GenreModel>>>()
                live.value = Error(DataErrorResponse())
                `when`(resource.asLiveData()).thenReturn(live)
                //Act
                val actual = sut.setGenreList().getOrAwaitValue() as Error<*>
                //Assert
                actual.dataResponse.run {
                    assertEquals(statusCode, INTERNAL_ERROR)
                    assertNull(apiTag)
                    assertNull(errorMessage)
                }
                verify(resource, single()).asLiveData()
                verifyZeroInteractions(dataManager)
                verifyNoMoreInteractions(resource)
            }
        }
    }

    @Test
    fun testUpdateGenresSuccess() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val tvNetwork = mock(TvGenreNetworkResource::class.java)
        val movieNetwork = mock(MovieGenreNetworkResource::class.java)
        val movieLive = MutableLiveData<DataState<List<GenreModel>>>()
        val tvLive = MutableLiveData<DataState<List<GenreModel>>>()
        sut.tvGenreNetworkResource = tvNetwork
        sut.movieGenreNetworkResource = movieNetwork
        movieLive.value = Success(DataSuccessResponse(getGenreList()))
        tvLive.value = Success(DataSuccessResponse(getGenreList()))

        `when`(tvNetwork.asLiveData()).thenReturn(tvLive)
        `when`(movieNetwork.asLiveData()).thenReturn(movieLive)
        //Act
        sut.updateGenres().getOrAwaitValue()
        //Assert
        verifyZeroInteractions(dataManager)
        verify(tvNetwork, single()).asLiveData()
        verify(movieNetwork, single()).asLiveData()
        verifyNoMoreInteractions(tvNetwork)
        verifyNoMoreInteractions(movieNetwork)
    }

    @Test
    fun testUpdateGenresErrorTvGenre() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val tvNetwork = mock(TvGenreNetworkResource::class.java)
        val movieNetwork = mock(MovieGenreNetworkResource::class.java)
        val movieLive = MutableLiveData<DataState<List<GenreModel>>>()
        val tvLive = MutableLiveData<DataState<List<GenreModel>>>()
        sut.tvGenreNetworkResource = tvNetwork
        sut.movieGenreNetworkResource = movieNetwork
        movieLive.value = Success(DataSuccessResponse(getGenreList()))
        tvLive.value = Error(DataErrorResponse())

        `when`(tvNetwork.asLiveData()).thenReturn(tvLive)
        `when`(movieNetwork.asLiveData()).thenReturn(movieLive)
        //Act
        sut.updateGenres().getOrAwaitValue()
        //Assert
        verifyZeroInteractions(dataManager)
        verify(tvNetwork, single()).asLiveData()
        verify(movieNetwork, single()).asLiveData()
        verifyNoMoreInteractions(tvNetwork)
        verifyNoMoreInteractions(movieNetwork)
    }

    @Test
    fun testUpdateGenresErrorMovieGenre() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val tvNetwork = mock(TvGenreNetworkResource::class.java)
        val movieNetwork = mock(MovieGenreNetworkResource::class.java)
        val movieLive = MutableLiveData<DataState<List<GenreModel>>>()
        val tvLive = MutableLiveData<DataState<List<GenreModel>>>()
        sut.tvGenreNetworkResource = tvNetwork
        sut.movieGenreNetworkResource = movieNetwork
        movieLive.value = Error(DataErrorResponse())
        tvLive.value = Success(DataSuccessResponse(getGenreList()))

        `when`(tvNetwork.asLiveData()).thenReturn(tvLive)
        `when`(movieNetwork.asLiveData()).thenReturn(movieLive)
        //Act
        sut.updateGenres().getOrAwaitValue()
        //Assert
        verifyZeroInteractions(dataManager)
        verify(tvNetwork, single()).asLiveData()
        verify(movieNetwork, single()).asLiveData()
        verifyNoMoreInteractions(tvNetwork)
        verifyNoMoreInteractions(movieNetwork)
    }

    @Test
    fun testUpdateGenresErrorMovieTvGenre() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val tvNetwork = mock(TvGenreNetworkResource::class.java)
        val movieNetwork = mock(MovieGenreNetworkResource::class.java)
        val movieLive = MutableLiveData<DataState<List<GenreModel>>>()
        val tvLive = MutableLiveData<DataState<List<GenreModel>>>()
        sut.tvGenreNetworkResource = tvNetwork
        sut.movieGenreNetworkResource = movieNetwork
        movieLive.value = Error(DataErrorResponse())
        tvLive.value = Error(DataErrorResponse())

        `when`(tvNetwork.asLiveData()).thenReturn(tvLive)
        `when`(movieNetwork.asLiveData()).thenReturn(movieLive)
        //Act
        sut.updateGenres().getOrAwaitValue()
        //Assert
        verifyZeroInteractions(dataManager)
        verify(tvNetwork, single()).asLiveData()
        verify(movieNetwork, single()).asLiveData()
        verifyNoMoreInteractions(tvNetwork)
        verifyNoMoreInteractions(movieNetwork)
    }
}