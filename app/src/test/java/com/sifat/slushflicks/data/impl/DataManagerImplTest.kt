package com.sifat.slushflicks.data.impl

import com.sifat.slushflicks.data.DataManager
import com.sifat.slushflicks.data.DatabaseManager
import com.sifat.slushflicks.data.FireStoreManager
import com.sifat.slushflicks.data.LocalDataManager
import com.sifat.slushflicks.model.MovieCollectionModel
import com.sifat.slushflicks.model.TvCollectionModel
import com.sifat.slushflicks.rule.MainCoroutineRule
import com.sifat.slushflicks.ui.helper.getMovieDetails
import com.sifat.slushflicks.ui.helper.getTvDetails
import com.sifat.slushflicks.utils.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Rule
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.ArgumentMatchers.anyList
import org.mockito.ArgumentMatchers.anyLong
import org.mockito.Mockito.*

@ExperimentalCoroutinesApi
internal class DataManagerImplTest {

    private lateinit var sut: DataManager
    private lateinit var firebaseManager: FireStoreManager
    private lateinit var databaseManager: DatabaseManager
    private lateinit var localDataManager: LocalDataManager

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    @BeforeEach
    fun setUp() {
        databaseManager = mock(DatabaseManager::class.java)
        localDataManager = mock(LocalDataManager::class.java)
        firebaseManager = mock(FireStoreManager::class.java)
        sut = DataManagerImpl(
            localDataManager = localDataManager,
            databaseManager = databaseManager,
            fireStoreManager = firebaseManager
        )
    }

    @Test
    fun testAddingGenre() {
        // Arrange
        val list = getGenreList()
        //Act
        sut.addGenre(list)
        //Assert
        verify(localDataManager, times(1)).addGenre(anyList())
        verifyNoMoreInteractions(localDataManager)
    }

    @Test
    fun testAddingSingleGenre() {
        // Arrange
        val genreId = 1L
        val genreName = "genreName"
        //Act
        sut.addGenre(genreId, genreName)
        //Assert
        verify(localDataManager, times(1)).addGenre(anyLong(), anyString())
        verifyNoMoreInteractions(localDataManager)
    }

    @Test
    fun testGettingGenre() {
        // Arrange
        val genreId = 1L
        val genreName = "genreName"
        `when`(localDataManager.getGenre(genreId)).thenReturn(genreName)
        //Act
        val actual = sut.getGenre(genreId)
        //Assert
        assertEquals(genreName, actual)
        verify(localDataManager, times(1)).getGenre(anyLong())
        verifyNoMoreInteractions(localDataManager)
    }

    @Test
    fun testInitGenres() {
        // Arrange
        val genreList = getGenreList()
        //Act
        sut.initGenres(genreList)
        //Assert
        verify(localDataManager, times(1)).initGenres(anyList())
        verifyNoMoreInteractions(localDataManager)
    }

    @Test
    fun testGettingGenres() {
        // Arrange
        val genreMap = getGenreMap()
        `when`(localDataManager.getGenres()).thenReturn(genreMap)
        //Act
        val actual = sut.getGenres()
        //Assert
        assertEquals(genreMap, actual)
        verify(localDataManager, times(1)).getGenres()
        verifyNoMoreInteractions(localDataManager)
    }

    @Test
    fun testSavingGenre() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val list = getGenreList()
        //Act
        sut.saveGenre(list)
        //Assert
        verify(databaseManager, times(1)).saveGenre(list)
        verify(localDataManager, times(1)).addGenre(anyList())
        verifyNoMoreInteractions(databaseManager)
        verifyNoMoreInteractions(localDataManager)
    }

    @Test
    fun testLoadingGenres() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val list = getGenreList()
        `when`(databaseManager.loadGenres()).thenReturn(list)
        //Act
        val actual = sut.loadGenres()
        //Assert
        assertEquals(list, actual)
        verify(databaseManager, times(1)).loadGenres()
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testInsertingNewMovieCollectionList() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val collectionName = "trending"
        val list = getMovieCollectionModel(collectionName)
        //Act
        sut.insertNewMovieCollection(collectionName, list)
        //Assert
        verify(databaseManager, times(1)).insertNewMovieCollection(collectionName, list)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testInsertingNewMovieCollection() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val collectionName = "trending"
        val movie = MovieCollectionModel(collectionName, 1, 0)
        //Act
        sut.insertNewMovieCollection(movie)
        //Assert
        verify(databaseManager, times(1)).insertNewMovieCollection(movie)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testInsertingNewTvCollectionList() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val collectionName = "trending"
        val list = getTvCollectionModel(collectionName)
        //Act
        sut.insertNewTvCollection(collectionName, list)
        //Assert
        verify(databaseManager, times(1)).insertNewTvCollection(collectionName, list)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testInsertingNewTvCollection() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val collectionName = "trending"
        val tvShow = TvCollectionModel(collectionName, 1, 0)
        //Act
        sut.insertNewTvCollection(tvShow)
        //Assert
        verify(databaseManager, times(1)).insertNewTvCollection(tvShow)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testSoftInsertionMovie() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val list = getMovieListModel()
        //Act
        sut.softInsertMovie(list)
        //Assert
        verify(databaseManager, times(1)).softInsertMovie(list)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testSoftInsertionTv() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val list = getTvListModel()
        //Act
        sut.softInsertTv(list)
        //Assert
        verify(databaseManager, times(1)).softInsertTv(list)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testGettingPagingMovies() {
        // Arrange
        val collectionName = "trending"
        //Act
        sut.getPagingMovies(collectionName)
        //Assert
        verify(databaseManager, times(1)).getPagingMovies(collectionName)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testGettingPagingTvShows() {
        // Arrange
        val collectionName = "trending"
        //Act
        sut.getPagingTvShows(collectionName)
        //Assert
        verify(databaseManager, times(1)).getPagingTvShows(collectionName)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testAddingMovieCollection() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val collectionName = "trending"
        val list = getMovieCollectionModel(collectionName)
        //Act
        sut.addMovieCollection(list)
        //Assert
        verify(databaseManager, times(1)).addMovieCollection(list)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testAddingTvCollection() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val collectionName = "trending"
        val list = getTvCollectionModel(collectionName)
        //Act
        sut.addTvCollection(list)
        //Assert
        verify(databaseManager, times(1)).addTvCollection(list)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testGettingMovieDetails() {
        // Arrange
        val movieId = 1L
        //Act
        sut.getMovieDetails(movieId)
        //Assert
        verify(databaseManager, times(1)).getMovieDetails(movieId)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testInsertionMovieDetails() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val movie = getMovieDetails(getMovieDetailsTestModel())!!
        //Act
        sut.insertMovieDetails(movie)
        //Assert
        verify(databaseManager, times(1)).insertMovieDetails(movie)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testUpdatingMovieDetails() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val movie = getMovieDetails(getMovieDetailsTestModel())!!
        //Act
        sut.updateMovieDetails(movie)
        //Assert
        verify(databaseManager, times(1)).updateMovieDetails(movie)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testUpdatingMovieCast() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val movieId = 1L
        val casts = getCastList()
        //Act
        sut.updateMovieDetails(casts, movieId)
        //Assert
        verify(databaseManager, times(1)).updateMovieDetails(casts, movieId)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testUpdateMovieVideo() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val movieId = 1L
        val video = getVideoModel()
        //Act
        sut.updateMovieDetails(video, movieId)
        //Assert
        verify(databaseManager, times(1)).updateMovieDetails(video, movieId)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testGettingMovieCollections() {
        // Arrange
        //Act
        sut.getMovieCollections()
        //Assert
        verify(firebaseManager, times(1)).getMovieCollections()
        verifyNoMoreInteractions(firebaseManager)
    }

    @Test
    fun testGettingTvCollections() {
        // Arrange
        //Act
        sut.getTvCollections()
        //Assert
        verify(firebaseManager, times(1)).getTvCollections()
        verifyNoMoreInteractions(firebaseManager)
    }


    @Test
    fun testGettingTvDetails() {
        // Arrange
        val tvShowId = 1L
        //Act
        sut.getTvShowDetails(tvShowId)
        //Assert
        verify(databaseManager, times(1)).getTvShowDetails(tvShowId)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testUpdatingTvDetails() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val tvShow = getTvDetails(getTvDetailsTestModel())!!
        //Act
        sut.updateTvDetails(tvShow)
        //Assert
        verify(databaseManager, times(1)).updateTvDetails(tvShow)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testUpdatingTvCast() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val tvShowId = 1L
        val casts = getCastList()
        //Act
        sut.updateTvDetails(casts, tvShowId)
        //Assert
        verify(databaseManager, times(1)).updateTvDetails(casts, tvShowId)
        verifyNoMoreInteractions(databaseManager)
    }

    @Test
    fun testUpdateTvVideo() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val tvShowId = 1L
        val video = getVideoModel()
        //Act
        sut.updateTvDetails(video, tvShowId)
        //Assert
        verify(databaseManager, times(1)).updateTvDetails(video, tvShowId)
        verifyNoMoreInteractions(databaseManager)
    }
}