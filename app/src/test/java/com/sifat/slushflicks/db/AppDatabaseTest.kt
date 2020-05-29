package com.sifat.slushflicks.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.sifat.slushflicks.rule.MainCoroutineRule
import org.junit.After
import org.junit.Rule

open class AppDatabaseTest {

    lateinit var appDatabase: AppDatabase

    @get:Rule
    val mainCoroutineDispatcher = MainCoroutineRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    open fun setup() {
        appDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            AppDatabase::class.java
        ).allowMainThreadQueries().build()
    }

    @After
    fun tearDown() {
        appDatabase.close()
    }

    fun getGenreDao() = appDatabase.getGenreDao()

    fun getMovieCollectionDao() = appDatabase.getMovieCollectionDao()

    fun getTvCollectionDao() = appDatabase.getTvCollectionDao()

    fun getMovieDao() = appDatabase.getMovieDao()

    fun getTvDao() = appDatabase.getTvDao()
}