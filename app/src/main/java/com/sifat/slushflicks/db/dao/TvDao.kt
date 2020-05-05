package com.sifat.slushflicks.db.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Query
import com.sifat.slushflicks.db.DbConstant
import com.sifat.slushflicks.model.MovieModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.model.TvModel

@Dao
interface TvDao : BaseDao<TvModel> {
    @Query("SELECT * FROM ${DbConstant.TableName.TABLE_NAME_TV} INNER JOIN ${DbConstant.TableName.TABLE_NAME_TV_TYPE} ON ${DbConstant.TableName.TABLE_NAME_TV}.id = ${DbConstant.TableName.TABLE_NAME_TV_TYPE}.id WHERE ${DbConstant.TableName.TABLE_NAME_TV_TYPE}.collection= :collection ORDER BY ${DbConstant.TableName.TABLE_NAME_TV_TYPE}.`index`")
    suspend fun getTvShows(collection: String): List<MovieModel>?

    @Query("SELECT ${DbConstant.TableName.TABLE_NAME_TV}.id, title, overview, voteAvg, backdropPath, genres FROM ${DbConstant.TableName.TABLE_NAME_TV} INNER JOIN ${DbConstant.TableName.TABLE_NAME_TV_TYPE} ON ${DbConstant.TableName.TABLE_NAME_TV}.id = ${DbConstant.TableName.TABLE_NAME_TV_TYPE}.id WHERE ${DbConstant.TableName.TABLE_NAME_TV_TYPE}.collection= :collection ORDER BY ${DbConstant.TableName.TABLE_NAME_TV_TYPE}.`index`")
    fun getPagedTvShowSource(collection: String): DataSource.Factory<Int, ShowModelMinimal>
}