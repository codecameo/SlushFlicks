package com.example.slushflicks.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_MOVIE_TYPE
import com.example.slushflicks.model.MovieCollectionModel

@Dao
interface CollectionDao : BaseDao<MovieCollectionModel> {

    //Performing atomic transaction
    @Transaction
    suspend fun saveMovieCollectionList(
        collectionName: String,
        movieCollection: List<MovieCollectionModel>
    ) {
        deleteCollection(collectionName)
        insertReplace(movieCollection)
    }

    @Query(value = "DELETE FROM $TABLE_NAME_MOVIE_TYPE WHERE collection = :label")
    suspend fun deleteCollection(label: String)

}