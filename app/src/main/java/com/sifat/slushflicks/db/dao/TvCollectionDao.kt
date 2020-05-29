package com.sifat.slushflicks.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.sifat.slushflicks.db.DbConstant
import com.sifat.slushflicks.model.TvCollectionModel
import org.jetbrains.annotations.TestOnly

@Dao
interface TvCollectionDao : BaseDao<TvCollectionModel> {

    //Performing atomic transaction
    @Transaction
    suspend fun saveTvCollectionList(
        collectionName: String,
        tvCollection: List<TvCollectionModel>
    ) {
        deleteCollection(collectionName)
        insertReplace(tvCollection)
    }

    @Query(value = "DELETE FROM ${DbConstant.TableName.TABLE_NAME_TV_TYPE} WHERE collection = :label")
    suspend fun deleteCollection(label: String)

    @TestOnly
    @Query(value = "SELECT * FROM ${DbConstant.TableName.TABLE_NAME_TV_TYPE}")
    suspend fun getCollections(): List<TvCollectionModel>
}