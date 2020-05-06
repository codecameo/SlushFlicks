package com.sifat.slushflicks.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sifat.slushflicks.model.CastModel
import com.sifat.slushflicks.model.EpisodeModel
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.model.SeasonModel

object RoomConverter {

    @TypeConverter
    @JvmStatic
    fun genresToString(genres: List<GenreModel>): String {
        return Gson().toJson(genres)
    }

    @TypeConverter
    @JvmStatic
    fun stringToGenres(genres: String?): List<GenreModel> {
        val token = object : TypeToken<List<GenreModel>>() {}.type
        return Gson().fromJson<List<GenreModel>>(genres, token)
    }

    @TypeConverter
    @JvmStatic
    fun castsToString(casts: List<CastModel>): String {
        return Gson().toJson(casts)
    }

    @TypeConverter
    @JvmStatic
    fun stringToCasts(casts: String?): List<CastModel> {
        val token = object : TypeToken<List<CastModel>>() {}.type
        return Gson().fromJson<List<CastModel>>(casts, token)
    }

    @TypeConverter
    @JvmStatic
    fun seasonsToString(seasons: List<SeasonModel>): String {
        return Gson().toJson(seasons)
    }

    @TypeConverter
    @JvmStatic
    fun stringToSeasons(seasons: String?): List<SeasonModel> {
        val token = object : TypeToken<List<SeasonModel>>() {}.type
        return Gson().fromJson<List<SeasonModel>>(seasons, token)
    }

    @TypeConverter
    @JvmStatic
    fun episodeToString(episodeModel: EpisodeModel): String {
        return Gson().toJson(episodeModel)
    }

    @TypeConverter
    @JvmStatic
    fun stringToEpisode(episode: String?): EpisodeModel {
        return Gson().fromJson(episode, EpisodeModel::class.java)
    }
}