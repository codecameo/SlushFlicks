package com.sifat.slushflicks.api.home.tv

import androidx.lifecycle.LiveData
import com.sifat.slushflicks.api.ApiEndPoint.Companion.RELATED_TV_SHOW_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.TRENDING_TV_SHOW_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.TV_CREDITS_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.TV_SHOW_COLLECTION_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.TV_SHOW_DETAILS_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.TV_SHOW_REVIEWS_URL
import com.sifat.slushflicks.api.ApiEndPoint.Companion.TV_VIDEO_URL
import com.sifat.slushflicks.api.ApiRequest.Companion.PATH_COLLECTION
import com.sifat.slushflicks.api.ApiRequest.Companion.PATH_RELATION_TYPE
import com.sifat.slushflicks.api.ApiRequest.Companion.PATH_TV_SEASON_NUMBER
import com.sifat.slushflicks.api.ApiRequest.Companion.PATH_TV_SHOW_ID
import com.sifat.slushflicks.api.ApiRequest.Companion.QUERY_KEY_API_KEY
import com.sifat.slushflicks.api.ApiRequest.Companion.QUERY_KEY_PAGE
import com.sifat.slushflicks.api.ApiResponse
import com.sifat.slushflicks.api.ApiTag.Companion.TV_CREDITS_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.TV_SHOW_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.TV_SHOW_REVIEWS_API_TAG
import com.sifat.slushflicks.api.ApiTag.Companion.TV_VIDEO_API_TAG
import com.sifat.slushflicks.api.details.model.CreditsApiModel
import com.sifat.slushflicks.api.details.model.ReviewListApiModel
import com.sifat.slushflicks.api.details.model.VideoListApiModel
import com.sifat.slushflicks.api.home.tv.model.TvListApiModel
import com.sifat.slushflicks.api.home.tv.model.TvShowDetailsApiModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Tag

interface TvService {

    @GET(TRENDING_TV_SHOW_URL)
    fun getTrendingTvShow(
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PAGE) page: Int,
        @Tag tag: String
    ): LiveData<ApiResponse<TvListApiModel>>

    @GET(TV_SHOW_COLLECTION_URL)
    fun getTvShowList(
        @Path(PATH_COLLECTION) collection: String,
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PAGE) page: Int,
        @Tag tag: String
    ): LiveData<ApiResponse<TvListApiModel>>

    @GET(TV_SHOW_DETAILS_URL)
    fun getTvShowDetails(
        @Path(PATH_TV_SHOW_ID) tvShowId: Long,
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Tag tag: String = TV_SHOW_API_TAG
    ): LiveData<ApiResponse<TvShowDetailsApiModel>>

    @GET(TV_CREDITS_URL)
    fun getTvShowCredits(
        @Path(PATH_TV_SHOW_ID) tvShowId: Long,
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Tag tag: String = TV_CREDITS_API_TAG
    ): LiveData<ApiResponse<CreditsApiModel>>

    @GET(TV_VIDEO_URL)
    fun getTvShowVideos(
        @Path(PATH_TV_SHOW_ID) tvShowId: Long,
        @Path(PATH_TV_SEASON_NUMBER) season: Int,
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Tag tag: String = TV_VIDEO_API_TAG
    ): LiveData<ApiResponse<VideoListApiModel>>

    @GET(RELATED_TV_SHOW_URL)
    fun getRelatedTvShows(
        @Path(PATH_TV_SHOW_ID) tvShowId: Long,
        @Path(PATH_RELATION_TYPE) relation: String,
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PAGE) page: Int,
        @Tag tag: String
    ): LiveData<ApiResponse<TvListApiModel>>

    @GET(TV_SHOW_REVIEWS_URL)
    fun getTvShowReviews(
        @Path(PATH_TV_SHOW_ID) tvShowId: Long,
        @Query(QUERY_KEY_API_KEY) apiKey: String,
        @Query(QUERY_KEY_PAGE) page: Int,
        @Tag tag: String = TV_SHOW_REVIEWS_API_TAG
    ): Call<ReviewListApiModel>
}