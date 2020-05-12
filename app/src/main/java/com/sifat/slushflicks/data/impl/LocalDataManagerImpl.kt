package com.sifat.slushflicks.data.impl

import android.content.Context
import com.sifat.slushflicks.data.LocalDataManager
import com.sifat.slushflicks.di.app.AppScope
import com.sifat.slushflicks.model.GenreModel
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

    override fun addGenre(genres: List<GenreModel>) {
        for (genre in genres) {
            genreMap[genre.id] = genre.name
        }
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