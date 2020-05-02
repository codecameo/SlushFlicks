package com.sifat.slushflicks.api

import com.sifat.slushflicks.api.ApiEndPoint.Companion.GENRES_MOVIE_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.GENRES_TV_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.MOVIE_CREDITS_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.MOVIE_DETAILS_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.MOVIE_VIDEOS_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.NOW_PLAYING_MOVIE_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.POPULAR_MOVIE_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.TOP_RATED_MOVIE_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.TRENDING_MOVIE_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.UPCOMING_MOVIE_URL
import com.sifat.slushflicks.api.ApiRequest.Companion.PATH_COLLECTION
import com.sifat.slushflicks.api.ApiRequest.Companion.PATH_MOVIE_ID
import com.sifat.slushflicks.api.MethodName.Companion.GET

enum class ImageDimension(val dimension : String) {
    // "w92", "w154", "w185", "w342", "w500", "w780", or "original"
    W92("/w92"),
    W154("/w154"),
    W185("/w185"),
    W342("w342"),
    W500("/w500"),
    W780("/w780"),
    Original("/original")
}

class ApiRequest {
    companion object {
        const val QUERY_KEY_API_KEY = "api_key"
        const val QUERY_KEY_PAGE = "page"
        const val PATH_COLLECTION = "collection"
        const val PATH_MOVIE_ID = "movie_id"
    }
}

class ApiEndPoint {
    companion object {
        const val TRENDING_MOVIE_URL = "trending/movie/day"
        const val MOVIE_DETAILS_URL = "movie/{$PATH_MOVIE_ID}"
        const val MOVIE_COLLECTION_URL = "movie/{$PATH_COLLECTION}"
        const val MOVIE_VIDEOS_URL = "movie/{$PATH_MOVIE_ID}/videos"
        const val MOVIE_CREDITS_URL = "movie/{$PATH_MOVIE_ID}/credits"
        const val GENRES_MOVIE_URL = "genre/movie/list"
        const val GENRES_TV_URL = "genre/tv/list"
        const val POPULAR_MOVIE_URL = "movie/popular"
        const val NOW_PLAYING_MOVIE_URL = "movie/now_playing"
        const val TOP_RATED_MOVIE_URL = "movie/top_rated"
        const val UPCOMING_MOVIE_URL = "movie/upcoming"
    }
}

class MethodName {
    companion object {
        const val GET = 1
    }
}

class ApiTag {
    companion object {
        const val TRENDING_API_TAG = TRENDING_MOVIE_URL + GET
        const val TV_GENRE_API_TAG = GENRES_TV_URL + GET
        const val MOVIE_GENRE_API_TAG = GENRES_MOVIE_URL + GET
        const val POPULAR_API_TAG = POPULAR_MOVIE_URL + GET
        const val NOW_PLAYING_API_TAG = NOW_PLAYING_MOVIE_URL + GET
        const val UPCOMING_API_TAG = UPCOMING_MOVIE_URL + GET
        const val TOP_RATED_API_TAG = TOP_RATED_MOVIE_URL + GET
        const val MOVIE_DETAIL_API_TAG = MOVIE_DETAILS_URL + GET
        const val MOVIE_VIDEO_API_TAG = MOVIE_VIDEOS_URL + GET
        const val MOVIE_CREDITS_API_TAG = MOVIE_CREDITS_URL + GET
    }
}

class StatusCode {
    companion object {
        const val SUCCESS = 200
        const val EMPTY_RESPONSE = 204
        const val RESOURCE_NOT_FOUND = 404
        const val UNAUTHORIZED = 401
        const val INTERNAL_ERROR = 105
        const val REQUEST_CANCELLED = 100
    }
}