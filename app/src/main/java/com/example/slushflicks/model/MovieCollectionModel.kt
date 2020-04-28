package com.example.slushflicks.model

import androidx.room.Entity
import com.example.slushflicks.db.DbConstant
import com.example.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_MOVIE_TYPE

/**
 * @param label represents the movie collection (popular, trending, top-rated etc
 * @param id is  the id of the movie
 * @param index represent the index of the movie in that collection
 * */
@Entity(tableName = TABLE_NAME_MOVIE_TYPE, primaryKeys = ["label", "id"])
data class MovieCollectionModel(
    val label : String,
    val id : Long,
    val index : Int
)