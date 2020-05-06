package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.api.home.tv.model.*
import com.sifat.slushflicks.model.*
import com.sifat.slushflicks.ui.state.MetaData
import com.sifat.slushflicks.utils.*


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

fun getTvDetails(apiModel: TvShowDetailsApiModel?): TvModel? {
    return apiModel?.run {
        TvModel(
            id = apiModel.id,
            backdropPath = getListImageUrl(apiModel.backdropPath),
            posterPath = getListImageUrl(apiModel.posterPath),
            popularity = apiModel.popularity,
            voteAvg = apiModel.voteAvg,
            voteCount = apiModel.voteCount,
            genres = apiModel.genres,
            status = apiModel.status,
            nextEpisode = getEpisode(apiModel.nextEpisode),
            lastEpisode = getEpisode(apiModel.lastEpisode),
            seasons = getSeasons(apiModel.seasons),
            numOfSeason = apiModel.seasonCount,
            numOfEpisode = apiModel.episodeCount,
            title = apiModel.name,
            overview = apiModel.overview,
            releaseData = apiModel.firstAirDate,
            directors = getDirectors(apiModel.createdBy),
            runtime = getRuntime(apiModel.episodeRunTime)
        )
    }
}

fun getDirectors(createdBy: List<CreatedBy>?): String {
    return if (!createdBy.isNullOrEmpty()) {
        val builder = StringBuilder(createdBy[0].name)
        for (index in 1 until createdBy.size) {
            builder.append(SPACE)
                .append(BULLET_SIGN)
                .append(SPACE)
                .append(createdBy[index].name)
        }
        builder.toString()
    } else {
        EMPTY_STRING
    }
}

fun getRuntime(runtimes: List<Int>?): Int {
    return runtimes?.let {
        var runtime = 0
        for (time in runtimes) {
            runtime += time
        }
        (runtime / runtimes.size)
    } ?: 0
}

fun getEpisode(model: Episode?): EpisodeModel? {
    return model?.let { episode ->
        EpisodeModel(
            id = episode.id,
            airDate = episode.airDate ?: EMPTY_STRING,
            name = episode.name,
            stillPath = episode.stillPath ?: EMPTY_STRING,
            overview = episode.overview,
            voteAvg = episode.voteAvg,
            seasonNumber = episode.seasonNumber,
            episodeNumber = episode.episodeNumber
        )
    }
}

fun getSeasons(models: List<Season>?): List<SeasonModel> {
    return models?.let { seasons ->
        val seasonModels = mutableListOf<SeasonModel>()
        for (season in seasons) {
            seasonModels.add(getSeason(season))
        }
        seasonModels
    } ?: emptyList()
}

fun getSeason(season: Season): SeasonModel {
    return SeasonModel(
        id = season.id,
        airDate = season.airDate ?: EMPTY_STRING,
        name = season.name,
        posterPath = season.posterPath ?: EMPTY_STRING,
        overview = season.overview,
        seasonNumber = season.seasonNumber,
        episodeCount = season.episodeCount
    )
}