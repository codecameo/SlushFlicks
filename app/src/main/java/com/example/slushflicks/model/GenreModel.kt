package com.example.slushflicks.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.slushflicks.db.DbConstant
import com.example.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_GENRE
import com.example.slushflicks.utils.EMPTY_STRING
import com.example.slushflicks.utils.INVALID_ID
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = TABLE_NAME_GENRE)
data class GenreModel(
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = false)
    val id: Long = INVALID_ID.toLong(),
    @SerializedName("name")
    @Expose
    val name: String = EMPTY_STRING
)