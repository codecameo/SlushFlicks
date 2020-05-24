package com.sifat.slushflicks.db

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import org.junit.After
import org.junit.Before

open class AppDatabaseTest {

    lateinit var appDatabase: AppDatabase

    @Before
    fun setup() {
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