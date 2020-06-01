package com.sifat.slushflicks.utils

import com.google.gson.Gson
import com.sifat.slushflicks.api.home.movie.model.MovieDetailsApiModel
import com.sifat.slushflicks.api.home.tv.model.TvShowDetailsApiModel
import com.sifat.slushflicks.model.*

fun getGenreMap(): Map<Long, String> {
    val map = HashMap<Long, String>()
    map[1] = "genre1"
    map[2] = "genre2"
    map[3] = "genre3"
    map[4] = "genre4"
    map[5] = "genre5"
    return map
}

fun getGenreList(): List<GenreModel> {
    val list = mutableListOf<GenreModel>()
    list.add(GenreModel(1, "Genre1"))
    list.add(GenreModel(2, "Genre2"))
    list.add(GenreModel(3, "Genre3"))
    list.add(GenreModel(4, "Genre4"))
    return list
}

fun getShowList(): List<ShowModelMinimal> {
    val list = mutableListOf<ShowModelMinimal>()
    list.add(
        ShowModelMinimal(
            id = 1,
            voteAvg = 7.8,
            title = "title1",
            overview = "overview1",
            backdropPath = "backdropPath1",
            genres = mutableListOf(
                GenreModel(1, "genre1"),
                GenreModel(2, "genre2"),
                GenreModel(3, "genre3")
            )
        )
    )
    list.add(
        ShowModelMinimal(
            id = 2,
            voteAvg = 6.4,
            title = "title2",
            overview = "overview2",
            backdropPath = "backdropPath2",
            genres = mutableListOf(
                GenreModel(2, "genre2"),
                GenreModel(5, "genre5")
            )
        )
    )
    list.add(
        ShowModelMinimal(
            id = 3,
            voteAvg = 8.1,
            title = "title3",
            overview = "overview3",
            backdropPath = "backdropPath3",
            genres = mutableListOf(
                GenreModel(3, "genre3"),
                GenreModel(4, "genre4")
            )
        )
    )
    return list
}

fun getTvListModel(): List<TvModel> {
    val list = mutableListOf<TvModel>()
    list.add(
        TvModel(
            id = 1,
            overview = "overview1",
            backdropPath = "backdropPath1",
            title = "title1",
            voteAvg = 7.8,
            genres = mutableListOf(
                GenreModel(1, "name1"),
                GenreModel(2, "name2"),
                GenreModel(3, "name3")
            )
        )
    )
    list.add(
        TvModel(
            id = 2,
            overview = "overview2",
            backdropPath = "backdropPath2",
            title = "title2",
            voteAvg = 8.5,
            genres = mutableListOf(
                GenreModel(4, "name4"),
                GenreModel(2, "name2")
            )
        )
    )
    list.add(
        TvModel(
            id = 3,
            overview = "overview3",
            backdropPath = "backdropPath3",
            title = "title3",
            voteAvg = 5.8,
            genres = mutableListOf(
                GenreModel(1, "name1"),
                GenreModel(5, "name5")
            )
        )
    )
    return list
}

fun getMovieListModel(): List<MovieModel> {
    val list = mutableListOf<MovieModel>()
    list.add(
        MovieModel(
            id = 1,
            overview = "overview1",
            backdropPath = "backdropPath1",
            title = "title1",
            voteAvg = 7.8,
            genres = mutableListOf(
                GenreModel(1, "name1"),
                GenreModel(2, "name2"),
                GenreModel(3, "name3")
            )
        )
    )
    list.add(
        MovieModel(
            id = 2,
            overview = "overview2",
            backdropPath = "backdropPath2",
            title = "title2",
            voteAvg = 8.5,
            genres = mutableListOf(
                GenreModel(4, "name4"),
                GenreModel(2, "name2")
            )
        )
    )
    list.add(
        MovieModel(
            id = 3,
            overview = "overview3",
            backdropPath = "backdropPath3",
            title = "title3",
            voteAvg = 5.8,
            genres = mutableListOf(
                GenreModel(1, "name1"),
                GenreModel(5, "name5")
            )
        )
    )
    return list
}

fun getCastList(): MutableList<CastModel> {
    val cast = mutableListOf<CastModel>()
    cast.add(CastModel(12, "iron", "downey", 2, "profileImage1"))
    cast.add(CastModel(22, "captain", "evans", 1, "profileImage1"))
    cast.add(CastModel(56, "thor", "crish", 4, "profileImage1"))
    return cast
}

fun getMovieDetailsTestModel(): MovieDetailsApiModel {
    return Gson().fromJson(movieDetailsResponse, MovieDetailsApiModel::class.java)
}

fun getMovieDiffDetailsTestModel(): MovieDetailsApiModel {
    return Gson().fromJson(movieDiffDetailsResponse, MovieDetailsApiModel::class.java)
}

fun getNullMovieDetailsTestModel(): MovieDetailsApiModel {
    return Gson().fromJson(movieDetailsNullResponse, MovieDetailsApiModel::class.java)
}

fun getTvDetailsTestModel(): TvShowDetailsApiModel {
    return Gson().fromJson(tvDetailsResponse, TvShowDetailsApiModel::class.java)
}

fun getTvDiffDetailsTestModel(): TvShowDetailsApiModel {
    return Gson().fromJson(tvDiffDetailsResponse, TvShowDetailsApiModel::class.java)
}

fun getNullTvDetailsTestModel(): TvShowDetailsApiModel {
    return Gson().fromJson(tvDetailsNullResponse, TvShowDetailsApiModel::class.java)
}

val movieDetailsResponse by lazy {
    """
            {
              "backdrop_path": "/ByDf0zjLSumz1MP1cDEo2JWVtU.jpg",
              "budget": 63000000,
              "genres": [
                {
                  "id": 28,
                  "name": "Action"
                },
                {
                  "id": 878,
                  "name": "Science Fiction"
                }
              ],
              "id": 603,
              "overview": "Set in the 22nd century",
              "popularity": 40.794,
              "poster_path": "/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
              "release_date": "1999-03-30",
              "revenue": 463517383,
              "runtime": 136,
              "status": "Released",
              "tagline": "Welcome to the Real World.",
              "title": "The Matrix",
              "video": false,
              "vote_average": 8.1,
              "vote_count": 17021
            }
        """.trimIndent()
}

val movieDiffDetailsResponse by lazy {
    """
            {
              "backdrop_path": "/ByDf0zjLSumz1MP1cDEo2JWVtU.jpg",
              "budget": 5347457,
              "genres": [
                {
                  "id": 27,
                  "name": "Drama"
                },
                {
                  "id": 78,
                  "name": "Crime"
                }
              ],
              "id": 603,
              "overview": "Set in the 22nd century",
              "popularity": 80.53,
              "poster_path": "/f89U3ADr1oiB1s9GkdPOEpXUk5H.jpg",
              "release_date": "2019-03-10",
              "revenue": 6547346,
              "runtime": 166,
              "status": "Hold",
              "tagline": "Welcome to the Duplicate World.",
              "title": "The Matrix",
              "video": false,
              "vote_average": 8.7,
              "vote_count": 346743
            }
        """.trimIndent()
}

val movieDetailsNullResponse by lazy {
    """
            {
              "backdrop_path": null,
              "budget": 63000000,
              "genres": [
                {
                  "id": 28,
                  "name": "Action"
                },
                {
                  "id": 878,
                  "name": "Science Fiction"
                }
              ],
              "id": 6603,
              "overview": "Set in the 22nd century.",
              "popularity": 40.794,
              "poster_path": null,
              "release_date": null,
              "revenue": 463517383,
              "runtime": 136,
              "status": "Released",
              "tagline": "Welcome to the Real World.",
              "title": "The Matrix 2",
              "video": false,
              "vote_average": 8.1,
              "vote_count": 17021
            }
        """.trimIndent()
}

private val tvDetailsResponse by lazy {
    """
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
            "id": 87784,
            "last_air_date": "2020-05-01",
            "last_episode_to_air": {
            "air_date": "2020-05-01",
            "episode_number": 4,
            "id": 2239042,
            "name": "Damage Control",
            "overview": "Andy takes matters into his own hands. Laurie reexamines the life she knew.",
            "season_number": 1,
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
            "number_of_episodes": 5,
            "number_of_seasons": 1,
            "overview": "A family’s lives are irreparably disrupted when the 14-year-old son is accused of murdering a fellow classmate in this dramatic legal thriller.",
            "popularity": 72.767,
            "poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
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
        """.trimIndent()
}

private val tvDiffDetailsResponse by lazy {
    """
            {
            "backdrop_path": "/9wCsImxDW4MCVP47vVjny0Wa06s.jpg",
            "created_by": [
            {
            "id": 18189,
            "credit_id": "5c92fafd0e0a261045e7ae95",
            "name": "Mark Bomback Bear",
            "gender": 2,
            "profile_path": "/6h43JZtgvcYrbl8FGHHPxeZUHht.jpg"
            }
            ],
            "episode_run_time": [
            60, 70
            ],
            "first_air_date": "2020-04-24",
            "genres": [
            {
            "id": 189,
            "name": "Science"
            },
            {
            "id": 968,
            "name": "Horror"
            }
            ],
            "id": 87784,
            "last_air_date": "2020-05-01",
            "last_episode_to_air": {
            "air_date": "2021-05-01",
            "episode_number": 6,
            "id": 2239042,
            "name": "Damage Control",
            "overview": "Andy takes matters into his own hands.",
            "season_number": 1,
            "still_path": "/AqV73RG7IWfwoY8CwGoRwn4wncp.jpg",
            "vote_average": 6.8,
            "vote_count": 23
            },
            "name": "Defending Jacob",
            "next_episode_to_air": {
            "air_date": "2020-06-08",
            "episode_number": 15,
            "id": 2244493,
            "name": "Episode 6",
            "overview": "Once upon a time",
            "production_code": "",
            "season_number": 1,
            "show_id": 87784,
            "still_path": null,
            "vote_average": 0,
            "vote_count": 10
            },
            "number_of_episodes": 33,
            "number_of_seasons": 3,
            "overview": "A family’s lives are irreparably disrupted when the 14-year-old son is accused of murdering a fellow classmate in this dramatic legal thriller.",
            "popularity": 82.767,
            "poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
            "seasons": [
            {
            "air_date": "2020-04-24",
            "episode_count": 5,
            "id": 120893,
            "name": "Season 1",
            "overview": "",
            "poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
            "season_number": 1
            },
            {
            "air_date": "2021-04-24",
            "episode_count": 5,
            "id": 46457,
            "name": "Season 2",
            "overview": "Season 2 overview",
            "poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
            "season_number": 1
            }
            ],
            "status": "Ended",
            "type": "Miniseries",
            "vote_average": 7.2,
            "vote_count": 785
            }
        """.trimIndent()
}

private val tvDetailsNullResponse by lazy {
    """
            {
            "backdrop_path": null,
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
            "first_air_date": null,
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
            "id": 863884,
            "last_air_date": "2020-05-01",
            "last_episode_to_air": {
            "air_date": "2020-05-01",
            "episode_number": 4,
            "id": 2239042,
            "name": "Damage Control",
            "overview": "Andy takes matters into his own hands. Laurie reexamines the life she knew.",
            "season_number": 1,
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
            "number_of_episodes": 5,
            "number_of_seasons": 1,
            "overview": "A family’s lives are irreparably disrupted when the 14-year-old son is accused of murdering a fellow classmate in this dramatic legal thriller.",
            "popularity": 72.767,
            "poster_path": null",
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
        """.trimIndent()
}