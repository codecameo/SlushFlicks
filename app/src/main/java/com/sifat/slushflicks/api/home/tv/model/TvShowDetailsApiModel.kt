package com.sifat.slushflicks.api.home.tv.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.sifat.slushflicks.model.GenreModel

data class TvShowDetailsApiModel(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("backdrop_path")
    @Expose
    val backdropPath: String?,
    @SerializedName("created_by")
    @Expose
    val createdBy: List<CreatedBy>,
    @SerializedName("episode_run_time")
    @Expose
    val episodeRunTime: List<Int>,
    @SerializedName("first_air_date")
    @Expose
    val firstAirDate: String?,
    @SerializedName("genres")
    @Expose
    val genres: List<GenreModel>,
    @SerializedName("last_episode_to_air")
    @Expose
    val lastEpisode: Episode,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("next_episode_to_air")
    @Expose
    val nextEpisode: Episode,
    @SerializedName("number_of_episodes")
    @Expose
    val episodeCount: Int,
    @SerializedName("number_of_seasons")
    @Expose
    val seasonCount: Int,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,
    @SerializedName("overview")
    @Expose
    val overview: String,
    @SerializedName("popularity")
    @Expose
    val popularity: Double,
    @SerializedName("seasons")
    @Expose
    val seasons: List<Season>,
    @SerializedName("vote_average")
    @Expose
    val voteAvg: Double,
    @SerializedName("status")
    @Expose
    val status: String,
    @SerializedName("vote_count")
    @Expose
    val voteCount: Int
)

data class CreatedBy(
    @SerializedName("name")
    @Expose
    val name: String
)

data class Episode(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("still_path")
    @Expose
    val stillPath: String?,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("episode_number")
    @Expose
    val episodeNumber: Int,
    @SerializedName("air_date")
    @Expose
    val airDate: String?,
    @SerializedName("overview")
    @Expose
    val overview: String,
    @SerializedName("season_number")
    @Expose
    val seasonNumber: Int,
    @SerializedName("vote_average")
    @Expose
    val voteAvg: Double
)

data class Season(
    @SerializedName("id")
    @Expose
    val id: Long,
    @SerializedName("poster_path")
    @Expose
    val posterPath: String?,
    @SerializedName("name")
    @Expose
    val name: String,
    @SerializedName("episode_count")
    @Expose
    val episodeCount: Int,
    @SerializedName("air_date")
    @Expose
    val airDate: String?,
    @SerializedName("overview")
    @Expose
    val overview: String,
    @SerializedName("season_number")
    @Expose
    val seasonNumber: Int
)

/*
"air_date": "2020-05-01",
"episode_number": 4,
"id": 2239042,
"name": "Damage Control",
"overview": "Andy takes matters into his own hands. Laurie reexamines the life she knew.",
"production_code": "",
"season_number": 1,
"show_id": 87784,
"still_path": "/AqV73RG7IWfwoY8CwGoRwn4wncp.jpg",
"vote_average": 0,
"vote_count": 0
 * */

/*
"air_date": "2020-04-24",
"episode_count": 5,
"id": 120893,
"name": "Season 1",
"overview": "",
"poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
"season_number": 1
 * */

/*
{
"backdrop_path": "/9wCsImxDW4MCVP47vVjny0Wa06s.jpg",
"created_by": [
{
"id": 18189,
"credit_id": "5c92fafd0e0a261045e7ae95",
"name": "Mark Bomback",
"gender": 2,
"profile_path": "/6h43JZtgvcYrbl8FGHHPxeZUHht.jpg"
}
],
"episode_run_time": [
60
],
"first_air_date": "2020-04-24",
"genres": [
{
"id": 18,
"name": "Drama"
},
{
"id": 9648,
"name": "Mystery"
},
{
"id": 80,
"name": "Crime"
}
],
"homepage": "https://tv.apple.com/show/defending-jacob/umc.cmc.5h5mr0shyyqqahqdv55ywyilr",
"id": 87784,
"in_production": true,
"languages": [
"en"
],
"last_air_date": "2020-05-01",
"last_episode_to_air": {
"air_date": "2020-05-01",
"episode_number": 4,
"id": 2239042,
"name": "Damage Control",
"overview": "Andy takes matters into his own hands. Laurie reexamines the life she knew.",
"production_code": "",
"season_number": 1,
"show_id": 87784,
"still_path": "/AqV73RG7IWfwoY8CwGoRwn4wncp.jpg",
"vote_average": 0,
"vote_count": 0
},
"name": "Defending Jacob",
"next_episode_to_air": {
"air_date": "2020-05-08",
"episode_number": 5,
"id": 2243944,
"name": "Episode 5",
"overview": "",
"production_code": "",
"season_number": 1,
"show_id": 87784,
"still_path": null,
"vote_average": 0,
"vote_count": 0
},
"networks": [
{
"name": "Apple TV+",
"id": 2552,
"logo_path": "/4KAy34EHvRM25Ih8wb82AuGU7zJ.png",
"origin_country": "US"
}
],
"number_of_episodes": 5,
"number_of_seasons": 1,
"origin_country": [
"US"
],
"original_language": "en",
"original_name": "Defending Jacob",
"overview": "A family’s lives are irreparably disrupted when the 14-year-old son is accused of murdering a fellow classmate in this dramatic legal thriller.",
"popularity": 72.767,
"poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
"production_companies": [
{
"id": 10039,
"logo_path": "/b8w4LldQolMKiLZw4FQJBcXSDGI.png",
"name": "Anonymous Content",
"origin_country": "US"
},
{
"id": 9223,
"logo_path": "/1nfcdPtyI7j9Err6cfrXNvlONEK.png",
"name": "Paramount Television",
"origin_country": "US"
}
],
"seasons": [
{
"air_date": "2020-04-24",
"episode_count": 5,
"id": 120893,
"name": "Season 1",
"overview": "",
"poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
"season_number": 1
}
],
"status": "Returning Series",
"type": "Miniseries",
"vote_average": 8.2,
"vote_count": 75
}
 * */