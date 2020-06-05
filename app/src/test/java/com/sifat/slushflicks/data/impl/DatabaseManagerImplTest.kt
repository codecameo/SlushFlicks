package com.sifat.slushflicks.data.impl

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.data.DatabaseManager
import com.sifat.slushflicks.db.AppDatabase
import com.sifat.slushflicks.db.dao.*
import com.sifat.slushflicks.model.*
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.helper.getMovieDetails
import com.sifat.slushflicks.ui.helper.getTvDetails
import com.sifat.slushflicks.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.*
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class DatabaseManagerImplTest {

    lateinit var appDatabase: AppDatabase

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var movieDaoMock: MovieDao
    private lateinit var tvDaoMock: TvDao
    private lateinit var genreDaoMock: GenreDao
    private lateinit var movieCollectionDaoMock: MovieCollectionDao
    private lateinit var tvCollectionDaoMock: TvCollectionDao
    lateinit var manager: DatabaseManager

    @Before
    fun setup() {
        appDatabase = mock(AppDatabase::class.java)
        manager = DatabaseManagerImpl(appDatabase)
    }

    @Test
    fun testSavingGenre() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        genreDaoMock = mock(GenreDao::class.java)
        val list = getGenreList()
        `when`(appDatabase.getGenreDao()).thenReturn(genreDaoMock)
        //Act
        manager.saveGenre(list)
        //Assert
        verify(genreDaoMock).insertReplace(list)
        verifyNoMoreInteractions(genreDaoMock)
    }

    @Test
    fun testLoadingGenres() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        genreDaoMock = mock(GenreDao::class.java)
        val list = getGenreList()
        `when`(appDatabase.getGenreDao()).thenReturn(genreDaoMock)
        `when`(genreDaoMock.getAllGenres()).thenReturn(list)
        //Act
        val actual = manager.loadGenres()
        //Assert
        assertEquals(list, actual)
        verify(genreDaoMock, times(1)).getAllGenres()
    }

    @Test
    fun testInsertNewMovieCollectionList() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        movieCollectionDaoMock = mock(MovieCollectionDao::class.java)
        val collectionName = "collectionName"
        val list = getMovieCollectionModel(collectionName)
        `when`(appDatabase.getMovieCollectionDao()).thenReturn(movieCollectionDaoMock)

        //Act
        manager.insertNewMovieCollection(collectionName, list)

        //Assert
        verify(movieCollectionDaoMock, times(1)).saveMovieCollectionList(collectionName, list)
    }

    @Test
    fun testInsertNewMovieCollection() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        movieCollectionDaoMock = mock(MovieCollectionDao::class.java)
        val collectionName = "collectionName"
        val movie = MovieCollectionModel(collectionName, 1, 0)
        `when`(appDatabase.getMovieCollectionDao()).thenReturn(movieCollectionDaoMock)

        //Act
        manager.insertNewMovieCollection(movie)

        //Assert
        verify(movieCollectionDaoMock, times(1)).insertReplace(movie)
    }

    @Test
    fun testInsertNewTvCollectionList() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        tvCollectionDaoMock = mock(TvCollectionDao::class.java)
        val collectionName = "collectionName"
        val list = getTvCollectionModel(collectionName)
        `when`(appDatabase.getTvCollectionDao()).thenReturn(tvCollectionDaoMock)

        //Act
        manager.insertNewTvCollection(collectionName, list)

        //Assert
        verify(tvCollectionDaoMock, times(1)).saveTvCollectionList(collectionName, list)
    }

    @Test
    fun testInsertNewTvCollection() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        tvCollectionDaoMock = mock(TvCollectionDao::class.java)
        val collectionName = "collectionName"
        val tvShow = TvCollectionModel(collectionName, 1, 0)
        `when`(appDatabase.getTvCollectionDao()).thenReturn(tvCollectionDaoMock)

        //Act
        manager.insertNewTvCollection(tvShow)

        //Assert
        verify(tvCollectionDaoMock, times(1)).insertReplace(tvShow)
    }

    @Test
    fun testSoftInsertMovie() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val list = getMovieListModel()
        movieDaoMock = mock(MovieDao::class.java)
        `when`(appDatabase.getMovieDao()).thenReturn(movieDaoMock)
        //Act
        manager.softInsertMovie(list)
        //Assert
        verify(movieDaoMock, times(1)).insertIgnore(list)
    }

    @Test
    fun testSoftInsertTv() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val list = getTvListModel()
        tvDaoMock = mock(TvDao::class.java)
        `when`(appDatabase.getTvDao()).thenReturn(tvDaoMock)
        //Act
        manager.softInsertTv(list)
        //Assert
        verify(tvDaoMock, times(1)).insertIgnore(list)
    }

    @Test
    fun testPagingMovies() {
        // Arrange
        movieDaoMock = mock(MovieDao::class.java)
        val collectionName = "collectionName"
        val list = getShowList()
        `when`(appDatabase.getMovieDao()).thenReturn(movieDaoMock)

        `when`(movieDaoMock.getPagedMovieSource(collectionName)).thenReturn(
            getFakeMovieDataSource<Int, ShowModelMinimal>(
                list
            )
        )
        //Act
        manager.getPagingMovies(collectionName)
        //Assert
        verify(movieDaoMock, times(1)).getPagedMovieSource(collectionName)
    }

    @Test
    fun testPagingTvShows() {
        // Arrange
        tvDaoMock = mock(TvDao::class.java)
        val collectionName = "collectionName"
        val list = getShowList()
        `when`(appDatabase.getTvDao()).thenReturn(tvDaoMock)

        `when`(tvDaoMock.getPagedTvShowSource(collectionName)).thenReturn(
            getFakeMovieDataSource<Int, ShowModelMinimal>(
                list
            )
        )
        //Act
        manager.getPagingTvShows(collectionName)
        //Assert
        verify(tvDaoMock, times(1)).getPagedTvShowSource(collectionName)
    }

    @Test
    fun testAddMovieCollection() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        movieCollectionDaoMock = mock(MovieCollectionDao::class.java)
        val collectionName = "collectionName"
        val list = getMovieCollectionModel(collectionName)
        `when`(appDatabase.getMovieCollectionDao()).thenReturn(movieCollectionDaoMock)

        //Act
        manager.addMovieCollection(list)

        //Assert
        verify(movieCollectionDaoMock, times(1)).insertReplace(list)
    }

    @Test
    fun testAddTvCollection() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        tvCollectionDaoMock = mock(TvCollectionDao::class.java)
        val collectionName = "collectionName"
        val list = getTvCollectionModel(collectionName)
        `when`(appDatabase.getTvCollectionDao()).thenReturn(tvCollectionDaoMock)

        //Act
        manager.addTvCollection(list)

        //Assert
        verify(tvCollectionDaoMock, times(1)).insertReplace(list)
    }

    @Test
    fun testMovieDetails() {
        // Arrange
        movieDaoMock = mock(MovieDao::class.java)
        val expected = getMovieDetails(getMovieDetailsTestModel())
        val live = MutableLiveData<MovieModel>()
        live.value = expected
        `when`(appDatabase.getMovieDao()).thenReturn(movieDaoMock)
        `when`(movieDaoMock.listenToMovie(expected!!.id)).thenReturn(live)
        //Act
        val actual = manager.getMovieDetails(expected.id).getOrAwaitValue()
        //Assert
        assertEquals(expected, actual)
        verify(movieDaoMock, times(1)).listenToMovie(anyLong())
    }

    @Test
    fun testInsertMovieDetails() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        movieDaoMock = mock(MovieDao::class.java)
        val expected = getMovieDetails(getMovieDetailsTestModel())
        `when`(appDatabase.getMovieDao()).thenReturn(movieDaoMock)
        //Act
        manager.insertMovieDetails(expected!!)
        //Assert
        verify(movieDaoMock, times(1)).insertReplace(expected)
    }

    @Test
    fun testUpdateMovieDetails() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        movieDaoMock = mock(MovieDao::class.java)
        val expected = getMovieDetails(getMovieDetailsTestModel())
        `when`(appDatabase.getMovieDao()).thenReturn(movieDaoMock)
        //Act
        manager.updateMovieDetails(expected!!)
        //Assert
        verify(movieDaoMock, times(1)).updateOrInsert(expected)
    }

    @Test
    fun testUpdateMovieCastDetails() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        movieDaoMock = mock(MovieDao::class.java)
        val casts = getCastList()
        val movieId = 1L
        `when`(appDatabase.getMovieDao()).thenReturn(movieDaoMock)
        //Act
        manager.updateMovieDetails(casts, movieId)
        //Assert
        verify(movieDaoMock, times(1)).update(movieId, casts)
    }

    @Test
    fun testUpdateMovieVideoLink() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        movieDaoMock = mock(MovieDao::class.java)
        val video = getVideoModel()
        val movieId = 1L
        `when`(appDatabase.getMovieDao()).thenReturn(movieDaoMock)
        //Act
        manager.updateMovieDetails(video, movieId)
        //Assert
        verify(movieDaoMock, times(1)).update(movieId, video.key)
    }

    /*@Test
    fun getTvShowDetails() {
        // Arrange
        //Act
        //Assert
    }

    @Test
    fun updateTvDetails() {
        // Arrange
        //Act
        //Assert
    }

    @Test
    fun testUpdateTvDetails() {
        // Arrange
        //Act
        //Assert
    }

    @Test
    fun testUpdateTvDetails1() {
        // Arrange
        //Act
        //Assert
    }*/


    @Test
    fun testTvShowDetails() {
        // Arrange
        tvDaoMock = mock(TvDao::class.java)
        val expected = getTvDetails(getTvDetailsTestModel())
        val live = MutableLiveData<TvModel>()
        live.value = expected
        `when`(appDatabase.getTvDao()).thenReturn(tvDaoMock)
        `when`(tvDaoMock.listenToTvShow(expected!!.id)).thenReturn(live)
        //Act
        val actual = manager.getTvShowDetails(expected.id).getOrAwaitValue()
        //Assert
        assertEquals(expected, actual)
        verify(tvDaoMock, times(1)).listenToTvShow(anyLong())
    }

    @Test
    fun testUpdateTvDetails() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        tvDaoMock = mock(TvDao::class.java)
        val expected = getTvDetails(getTvDetailsTestModel())
        `when`(appDatabase.getTvDao()).thenReturn(tvDaoMock)
        //Act
        manager.updateTvDetails(expected!!)
        //Assert
        verify(tvDaoMock, times(1)).updateOrInsert(expected)
    }

    @Test
    fun testUpdateTvCastDetails() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        tvDaoMock = mock(TvDao::class.java)
        val casts = getCastList()
        val tvShowId = 1L
        `when`(appDatabase.getTvDao()).thenReturn(tvDaoMock)
        //Act
        manager.updateTvDetails(casts, tvShowId)
        //Assert
        verify(tvDaoMock, times(1)).update(tvShowId, casts)
    }

    @Test
    fun testUpdateTvVideoLink() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        tvDaoMock = mock(TvDao::class.java)
        val video = VideoApiModel("id001", "videokey", "type", "platform")
        val tvShowId = 1L
        `when`(appDatabase.getTvDao()).thenReturn(tvDaoMock)
        //Act
        manager.updateTvDetails(video, tvShowId)
        //Assert
        verify(tvDaoMock, times(1)).update(tvShowId, video.key)
    }
}