package com.sifat.slushflicks.db.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sifat.slushflicks.db.AppDatabaseTest
import com.sifat.slushflicks.model.MovieCollectionModel
import com.sifat.slushflicks.ui.helper.getMovieDetails
import com.sifat.slushflicks.utils.getMovieDetailsTestModel
import com.sifat.slushflicks.utils.getNullMovieDetailsTestModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MovieCollectionDaoTest : AppDatabaseTest() {

    lateinit var movieDaoTest: MovieDao
    lateinit var collectionDao: MovieCollectionDao

    @Before
    override fun setup() {
        super.setup()
        collectionDao = appDatabase.getMovieCollectionDao()
        movieDaoTest = appDatabase.getMovieDao()
    }

    @Test
    fun deleteMovieCollection() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val list1 = mutableListOf<MovieCollectionModel>()
        val list2 = mutableListOf<MovieCollectionModel>()
        val movie1 = getMovieDetailsTestModel()
        val movie2 = getNullMovieDetailsTestModel()
        getMovieDetails(movie1)?.let { movieDaoTest.insertIgnore(it) }
        getMovieDetails(movie2)?.let { movieDaoTest.insertIgnore(it) }

        list1.add(MovieCollectionModel("collectionName1", movie1.id, 0))
        list1.add(MovieCollectionModel("collectionName1", movie2.id, 1))
        list2.add(MovieCollectionModel("collectionName2", movie1.id, 0))
        list2.add(MovieCollectionModel("collectionName2", movie2.id, 1))

        // Action
        collectionDao.insertIgnore(list1)
        collectionDao.insertIgnore(list2)
        collectionDao.deleteCollection("collectionName1")
        val actual = collectionDao.getCollections()

        // Assert
        assertEquals(expected = list2, actual = actual)
    }

    @Test
    fun saveMovieCollectionOverridePreviousMovies() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val list1 = mutableListOf<MovieCollectionModel>()
        val list2 = mutableListOf<MovieCollectionModel>()
        val expectedList = mutableListOf<MovieCollectionModel>()
        val movie1 = getMovieDetailsTestModel()
        val movie2 = getNullMovieDetailsTestModel()
        getMovieDetails(movie1)?.let { movieDaoTest.insertIgnore(it) }
        getMovieDetails(movie2)?.let { movieDaoTest.insertIgnore(it) }

        list1.add(MovieCollectionModel("collectionName1", movie1.id, 0))
        list1.add(MovieCollectionModel("collectionName1", movie2.id, 1))
        list1.add(MovieCollectionModel("collectionName2", movie1.id, 0))

        list2.add(MovieCollectionModel("collectionName1", movie1.id, 0))

        expectedList.add(MovieCollectionModel("collectionName2", movie1.id, 0))
        expectedList.add(MovieCollectionModel("collectionName1", movie1.id, 0))

        // Action
        collectionDao.insertIgnore(list1)
        //collectionDao.saveMovieCollectionList("collectionName1", list2)
        collectionDao.deleteCollection("collectionName1")
        collectionDao.insertReplace(list2)
        val actual = collectionDao.getCollections()

        // Assert
        assertEquals(expected = expectedList, actual = actual)
    }


}