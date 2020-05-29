package com.sifat.slushflicks.db.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sifat.slushflicks.db.AppDatabaseTest
import com.sifat.slushflicks.model.GenreModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class GenreDaoTest : AppDatabaseTest() {
    lateinit var dao: GenreDao

    @Before
    override fun setup() {
        super.setup()
        dao = appDatabase.getGenreDao()
    }

    @Test
    fun testEmptyList() = mainCoroutineDispatcher.runBlockingTest {
        val list = dao.getAllGenres()
        assertEquals(emptyList(), list)
    }

    @Test
    fun testGenreList() = mainCoroutineDispatcher.runBlockingTest {
        // Arrange
        val expected = mutableListOf<GenreModel>()
        expected.add(GenreModel(1, " genre1"))
        expected.add(GenreModel(2, " genre2"))
        expected.add(GenreModel(3, " genre3"))

        // Action
        dao.insertReplace(expected)
        val actual = dao.getAllGenres()

        // Assert
        assertEquals(expected, actual)
    }
}