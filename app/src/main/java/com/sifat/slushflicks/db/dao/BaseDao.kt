package com.sifat.slushflicks.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy

@Dao
interface BaseDao<T> {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReplace(models: List<T>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReplace(model: T)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIgnore(models: List<T>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIgnore(model: T)
}