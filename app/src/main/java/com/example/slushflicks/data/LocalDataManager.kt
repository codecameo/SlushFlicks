package com.example.slushflicks.data

import com.example.slushflicks.model.GenreModel

interface LocalDataManager {

    fun addGenre(id : Long, name : String)

    fun getGenre(id : Long) : String?

    fun initGenres(genres : List<GenreModel>?)

    fun getGenres(): Map<Long, String>
}