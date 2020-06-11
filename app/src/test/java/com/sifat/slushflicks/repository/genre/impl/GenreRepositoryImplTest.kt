package com.sifat.slushflicks.repository.genre.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.sifat.slushflicks.api.ApiTag.Companion.MOVIE_GENRE_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.TV_GENRE_API_TAG
import com.sifat.slushflicks.api.home.genre.GenreService
import com.sifat.slushflicks.api.home.genre.model.GenreListApiModel
import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.repository.resource.impl.GenreNetworkResource
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.state.DataState
import com.sifat.slushflicks.ui.state.DataSuccessResponse
import com.sifat.slushflicks.utils.api.NetworkStateManager
import com.sifat.slushflicks.utils.eq
import com.sifat.slushflicks.utils.getGenreList
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.single
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.jupiter.api.Assertions
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.Response
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class GenreRepositoryImplTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    private lateinit var sut: GenreRepositoryImpl
    private lateinit var genreService: GenreService
    private lateinit var networkStateManager: NetworkStateManager
    private lateinit var dataManager: DataManager
    private val apiKey = "ApiKey"
    private lateinit var resource: GenreNetworkResource

    @Before
    fun setup() {
        dataManager = mock(DataManager::class.java)
        genreService = mock(GenreService::class.java)
        networkStateManager = mock(NetworkStateManager::class.java)
        resource = mock(GenreNetworkResource::class.java)
        sut = GenreRepositoryImpl(genreService, dataManager, apiKey, networkStateManager)
        sut.genreNetworkResource = resource
    }

    @Test
    fun testSettingGenreList() {
        Assertions.assertDoesNotThrow {
            mainCoroutineDispatcher.runBlockingTest {
                // Arrange
                val live = MutableLiveData<DataState<List<GenreModel>>>()
                val list = getGenreList()
                live.value = DataState.Success(DataSuccessResponse(data = list))
                `when`(resource.asLiveData()).thenReturn(live)
                //Act
                val actual = sut.setGenreList().getOrAwaitValue() as DataState.Success<*>
                //Assert
                assertEquals(actual.dataResponse.data, list)
                verify(resource, single()).asLiveData()
                verify(dataManager, single()).initGenres(anyList())
                verifyNoMoreInteractions(dataManager)
                verifyNoMoreInteractions(resource)
                verifyZeroInteractions(genreService)
                verifyZeroInteractions(networkStateManager)
            }
        }
    }

    @Test
    fun updateGenres() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        `when`(networkStateManager.isOnline()).thenReturn(true)

        val movieResponse = GenreListApiModel(getGenreList())
        val movieGenre = Response.success(movieResponse)
        `when`(genreService.getMovieGenre(apiKey)).thenReturn(movieGenre)

        val tvResponse = GenreListApiModel(getGenreList())
        val tvGenre = Response.success(tvResponse)
        `when`(genreService.getTvGenre(apiKey)).thenReturn(tvGenre)
        //Act
        sut.updateGenres(Main)
        //Assert
        verify(genreService, single()).getMovieGenre(anyString(), eq(MOVIE_GENRE_API_TAG))
        verify(genreService, single()).getTvGenre(anyString(), eq(TV_GENRE_API_TAG))
        verify(networkStateManager, single()).isOnline()
        verifyNoMoreInteractions(genreService)
        verify(dataManager, times(2)).saveGenre(anyList())
        verifyNoMoreInteractions(dataManager)
        verifyNoMoreInteractions(networkStateManager)
        verifyZeroInteractions(resource)
    }
}