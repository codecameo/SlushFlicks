package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.api.home.tv.model.TvApiModel
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.model.ShowModelMinimal
import com.sifat.slushflicks.model.TvCollectionModel
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.ui.state.MetaData
import com.sifat.slushflicks.utils.EMPTY_STRING
import com.sifat.slushflicks.utils.PAGE_SIZE
import com.sifat.slushflicks.utils.getListImageUrl


/**
 * This conversion discard unnecessary data returned from api
 * */
fun getTvList(tvApiModels: List<TvApiModel>?, genres: Map<Long, String>): List<TvModel> {
    val tvModels = mutableListOf<TvModel>()
    tvApiModels?.let {
        for (tv in tvApiModels) {
            val genresModels = getGenresModels(tv.genreIds, genres)
            val tvModel = TvModel(
                id = tv.id,
                backdropPath = getListImageUrl(tv.backdropPath),
                overview = tv.overview,
                genres = genresModels,
                popularity = tv.popularity,
                posterPath = getListImageUrl(tv.posterPath),
                releaseData = tv.releaseDate ?: EMPTY_STRING,
                title = tv.title,
                voteAvg = tv.voteAverage,
                voteCount = tv.voteCount
            )
            tvModels.add(tvModel)
        }
    }
    return tvModels
}

fun getCollectionModels(
    tvShows: List<TvModel>,
    collection: String,
    page: Int
): List<TvCollectionModel> {
    val collectionList = mutableListOf<TvCollectionModel>()
    for (index in tvShows.indices) {
        val collectionModel = TvCollectionModel(
            collection = collection,
            id = tvShows[index].id,
            index = ((page - 1) * PAGE_SIZE) + index
        )
        collectionList.add(collectionModel)
    }
    return collectionList
}

fun getMetaData(movieListApiModel: TvListApiModel?): MetaData? {
    return movieListApiModel?.let { model ->
        MetaData(
            page = model.page,
            totalResult = model.totalResults,
            totalPage = model.totalPages
        )
    }
}

fun getShowMinimalModel(tvShows: List<TvModel>?): List<ShowModelMinimal>? {
    if (tvShows.isNullOrEmpty()) return null
    val moviesMinimalList = mutableListOf<ShowModelMinimal>()
    for (tv in tvShows) {
        val movieModelMinimal = ShowModelMinimal(
            id = tv.id,
            overview = tv.overview,
            title = tv.title,
            genres = tv.genres,
            voteAvg = tv.voteAvg,
            backdropPath = tv.backdropPath
        )
        moviesMinimalList.add(movieModelMinimal)
    }
    return moviesMinimalList
}