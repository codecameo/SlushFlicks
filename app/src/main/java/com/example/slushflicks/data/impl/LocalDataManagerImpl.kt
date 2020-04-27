package com.example.slushflicks.data.impl

import android.content.Context
import com.example.slushflicks.data.LocalDataManager
import com.example.slushflicks.di.app.AppScope
import com.example.slushflicks.model.GenreModel
import javax.inject.Inject

@AppScope
class LocalDataManagerImpl
@Inject constructor(
    private val context: Context
) : LocalDataManager {

    private val genreMap = mutableMapOf<Long, String>()

    override fun addGenre(id: Long, name: String) {
        genreMap[id] = name
    }

    override fun getGenre(id: Long): String? {
        return genreMap[id]
    }

    override fun initGenres(genres: List<GenreModel>?) {
        if (!genres.isNullOrEmpty()) {
            genreMap.clear()
            for (genre in genres) {
                genreMap[genre.id] = genre.name
            }
        }
    }

    override fun getGenres(): Map<Long, String> {
        return genreMap
    }
}