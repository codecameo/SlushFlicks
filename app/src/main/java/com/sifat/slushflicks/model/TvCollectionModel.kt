package com.sifat.slushflicks.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.sifat.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_TV_TYPE

/**
 * @param collection represents the tv collection (popular, trending, top-rated etc
 * @param id is  the id of the tv
 * @param index represent the index of the movie in that collection
 * */
@Entity(
    tableName = TABLE_NAME_TV_TYPE,
    primaryKeys = ["collection", "id"],
    foreignKeys = [ForeignKey(
        entity = TvModel::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("id"),
        onDelete = ForeignKey.CASCADE
    )],
    indices = [Index(value = ["id"], unique = false)]
)
data class TvCollectionModel(
    @ColumnInfo(name = "collection")
    val collection: String,
    @ColumnInfo(name = "id")
    val id: Long,
    @ColumnInfo(name = "index")
    val index: Int
)