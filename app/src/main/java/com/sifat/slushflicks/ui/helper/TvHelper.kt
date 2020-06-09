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
            val tvModel = tv.run {
                TvModel(
                    id = id,
                    backdropPath = backdropPath ?: EMPTY_STRING,
                    overview = overview,
                    genres = genresModels,
                    popularity = popularity,
                    posterPath = posterPath ?: EMPTY_STRING,
                    releaseData = releaseDate ?: EMPTY_STRING,
                    title = title,
                    voteAvg = voteAverage,
                    voteCount = voteCount
                )
            }
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
    if (page < 1) throw RuntimeException(String.format(INVALID_PAGE, page))
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

fun getMetaData(tvListApiModel: TvListApiModel?): MetaData? {
    return tvListApiModel?.run {
        MetaData(
            page = page,
            totalResult = totalResults,
            totalPage = totalPages
        )
    }
}

fun getTvMinimalModel(
    tvShows: List<TvModel>?
): List<ShowModelMinimal>? {
    if (tvShows.isNullOrEmpty()) return null
    val moviesMinimalList = mutableListOf<ShowModelMinimal>()
    for (tv in tvShows) {
        val movieModelMinimal = tv.run {
            ShowModelMinimal(
                id = id,
                overview = overview,
                title = title,
                genres = genres,
                voteAvg = voteAvg,
                backdropPath = backdropPath
            )
        }
        moviesMinimalList.add(movieModelMinimal)
    }
    return moviesMinimalList
}

fun getTvShowMinimalApiModel(
    tvShow: TvApiModel,
    genreMap: Map<Long, String>
): ShowModelMinimal {
    val genresModels = getGenresModels(tvShow.genreIds, genreMap)
    return ShowModelMinimal(
        id = tvShow.id,
        overview = tvShow.overview,
        title = tvShow.title,
        genres = genresModels,
        voteAvg = tvShow.voteAverage,
        backdropPath = tvShow.backdropPath ?: EMPTY_STRING
    )
}

fun getTvDetails(apiModel: TvShowDetailsApiModel?): TvModel? {
    return apiModel?.run {
        TvModel(
            id = id,
            backdropPath = backdropPath ?: EMPTY_STRING,
            posterPath = posterPath ?: EMPTY_STRING,
            popularity = popularity,
            voteAvg = voteAvg,
            voteCount = voteCount,
            genres = genres,
            status = status,
            nextEpisode = getEpisode(nextEpisode),
            lastEpisode = getEpisode(lastEpisode),
            seasons = getSeasons(seasons),
            numOfSeason = seasonCount,
            numOfEpisode = episodeCount,
            title = name,
            overview = overview,
            releaseData = firstAirDate ?: EMPTY_STRING,
            directors = getDirectors(createdBy),
            runtime = getRuntime(episodeRunTime)
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
    return if (!runtimes.isNullOrEmpty()) {
        var runtime = 0
        for (time in runtimes) {
            runtime += time
        }
        (runtime / runtimes.size)
    } else 0
}

fun getEpisode(model: Episode?): EpisodeModel? {
    return model?.run {
        EpisodeModel(
            id = id,
            airDate = airDate ?: EMPTY_STRING,
            name = name,
            stillPath = stillPath ?: EMPTY_STRING,
            overview = overview,
            voteAvg = voteAvg,
            seasonNumber = seasonNumber,
            episodeNumber = episodeNumber
        )
    }
}

fun getSeasons(models: List<Season>?): List<SeasonModel> {
    return models?.let { seasons ->
        val seasonModels = mutableListOf<SeasonModel>()
        for (season in seasons) {
            if (season.seasonNumber != 0) seasonModels.add(getSeason(season))
        }
        seasonModels
    } ?: emptyList()
}

fun getSeason(season: Season): SeasonModel {
    return season.run {
        SeasonModel(
            id = id,
            airDate = airDate ?: EMPTY_STRING,
            name = name,
            posterPath = posterPath ?: EMPTY_STRING,
            overview = overview,
            seasonNumber = seasonNumber,
            episodeCount = episodeCount
        )
    }
}

/*
fun getTvShowMinimalApiModels(
    tvShows: List<TvApiModel>?,
    genreMap: Map<Long, String>
): List<ShowModelMinimal> {
    if (tvShows.isNullOrEmpty()) return emptyList()
    val tvShowsMinimalList = mutableListOf<ShowModelMinimal>()
    for (tvShow in tvShows) {
        val genresModels = getGenresModels(tvShow.genreIds, genreMap)
        val movieModelMinimal = ShowModelMinimal(
            id = tvShow.id,
            overview = tvShow.overview,
            title = tvShow.title,
            genres = genresModels,
            voteAvg = tvShow.voteAverage,
            backdropPath = tvShow.backdropPath ?: EMPTY_STRING
        )
        tvShowsMinimalList.add(movieModelMinimal)
    }
    return tvShowsMinimalList
}*/
