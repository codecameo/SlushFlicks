package com.sifat.slushflicks.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sifat.slushflicks.db.DbConstant.TableName.Companion.TABLE_NAME_GENRE
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.INVALID_ID

@Entity(tableName = TABLE_NAME_GENRE)
data class GenreModel(
    @SerializedName("id")
    @Expose
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    val id: Long = INVALID_ID.toLong(),
    @SerializedName("name")
    @Expose
    @ColumnInfo(name = "name")
    val name: String = EMPTY_STRING
)