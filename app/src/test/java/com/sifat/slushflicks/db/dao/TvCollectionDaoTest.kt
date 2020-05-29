package com.sifat.slushflicks.db.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sifat.slushflicks.db.AppDatabaseTest
import com.sifat.slushflicks.model.TvCollectionModel
import com.sifat.slushflicks.ui.helper.getTvDetails
import com.sifat.slushflicks.utils.getNullTvDetailsTestModel
import com.sifat.slushflicks.utils.getTvDetailsTestModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TvCollectionDaoTest : AppDatabaseTest() {

    lateinit var tvDaoTest: TvDao
    lateinit var collectionDao: TvCollectionDao

    @Before
    override fun setup() {
        super.setup()
        collectionDao = appDatabase.getTvCollectionDao()
        tvDaoTest = appDatabase.getTvDao()
    }

    @Test
    fun deleteTvCollection() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val list1 = mutableListOf<TvCollectionModel>()
        val list2 = mutableListOf<TvCollectionModel>()
        val tv1 = getTvDetailsTestModel()
        val tv2 = getNullTvDetailsTestModel()
        getTvDetails(tv1)?.let { tvDaoTest.insertIgnore(it) }
        getTvDetails(tv2)?.let { tvDaoTest.insertIgnore(it) }

        list1.add(TvCollectionModel("collectionName1", tv1.id, 0))
        list1.add(TvCollectionModel("collectionName1", tv2.id, 1))
        list2.add(TvCollectionModel("collectionName2", tv1.id, 0))
        list2.add(TvCollectionModel("collectionName2", tv2.id, 1))

        // Action
        collectionDao.insertIgnore(list1)
        collectionDao.insertIgnore(list2)
        collectionDao.deleteCollection("collectionName1")
        val actual = collectionDao.getCollections()

        // Assert
        assertEquals(expected = list2, actual = actual)
    }

    @Test
    fun saveTvCollectionOverridePreviousMovies() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val list1 = mutableListOf<TvCollectionModel>()
        val list2 = mutableListOf<TvCollectionModel>()
        val expectedList = mutableListOf<TvCollectionModel>()
        val tv1 = getTvDetailsTestModel()
        val tv2 = getNullTvDetailsTestModel()
        getTvDetails(tv1)?.let { tvDaoTest.insertIgnore(it) }
        getTvDetails(tv2)?.let { tvDaoTest.insertIgnore(it) }

        list1.add(TvCollectionModel("collectionName1", tv1.id, 0))
        list1.add(TvCollectionModel("collectionName1", tv2.id, 1))
        list1.add(TvCollectionModel("collectionName2", tv1.id, 0))

        list2.add(TvCollectionModel("collectionName1", tv1.id, 0))

        expectedList.add(TvCollectionModel("collectionName2", tv1.id, 0))
        expectedList.add(TvCollectionModel("collectionName1", tv1.id, 0))

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