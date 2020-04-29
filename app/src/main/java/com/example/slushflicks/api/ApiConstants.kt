package com.example.slushflicks.api

import com.example.slushflicks.api.ApiEndPoint.Companion.GENRES_MOVIE_URL
import com.example.slushflicks.api.ApiEndPoint.Companion.GENRES_TV_URL
import com.example.slushflicks.api.ApiEndPoint.Companion.NOW_PLAYING_MOVIE_URL
import com.example.slushflicks.api.ApiEndPoint.Companion.POPULAR_MOVIE_URL
import com.example.slushflicks.api.ApiEndPoint.Companion.TOP_RATED_MOVIE_URL
import com.example.slushflicks.api.ApiEndPoint.Companion.TRENDING_MOVIE_URL
import com.example.slushflicks.api.ApiEndPoint.Companion.UPCOMING_MOVIE_URL
import com.example.slushflicks.api.MethodName.Companion.GET

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
    }
}

class ApiEndPoint {
    companion object {
        const val TRENDING_MOVIE_URL = "trending/movie/day"
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