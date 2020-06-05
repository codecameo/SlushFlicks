package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.ui.base.ListViewState
import com.sifat.slushflicks.ui.home.adapter.model.ShowListModel
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.LOADING_MODEL_COUNT

fun getShowListLoadingModels(): List<ShowListModel> {
    val list = mutableListOf<ShowListModel>()
    for (count in 0..LOADING_MODEL_COUNT) {
        list.add(ShowListModel(null, ListViewState.LOADING))
    }
    return list
}

/**
 * This conversion converts model to List models for view state
 * */
fun getShowListModel(shows: List<ShowModelMinimal>?): List<ShowListModel> {
    val movieListModels = mutableListOf<ShowListModel>()
    shows?.let {
        for (movie in shows) {
            val movieListModel = ShowListModel(movie, ListViewState.VIEW)
            movieListModels.add(movieListModel)
        }
    }
    return movieListModels
}

fun getGenresModels(
    genreIds: List<Long>,
    genreMap: Map<Long, String>
): List<GenreModel> {
    val genres = mutableListOf<GenreModel>()
    for (id in genreIds) {
        val genre = GenreModel(
            id = id,
            name = genreMap[id] ?: EMPTY_STRING
        )
        genres.add(genre)
    }
    return genres
}