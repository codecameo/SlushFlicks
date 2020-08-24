package com.sifat.slushflicks.db

import com.sifat.slushflicks.model.CastModel
import com.sifat.slushflicks.model.EpisodeModel
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.model.SeasonModel
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

internal class RoomConverterTest {

    @Test
    fun testGenresListToString() {
        // Arrange
        val genres = mutableListOf<GenreModel>()
        genres.add(GenreModel(1, "Name1"))
        genres.add(GenreModel(2, "Name2"))
        genres.add(GenreModel(3, "Name3"))
        genres.add(GenreModel(4, "Name4"))
        val expected =
            """[{"id":1,"name":"Name1"},{"id":1,"name":"Name2"},{"id":3,"name":"Name3"},{"id":4,"name":"Name4"}]"""
        //Act
        val actual = RoomConverter.genresToString(genres)
        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun testGenresEmptyListToString() {
        // Arrange
        val genres = mutableListOf<GenreModel>()
        val expected = "[]"
        //Act
        val actual = RoomConverter.genresToString(genres)
        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun testStringToGenresList() {
        // Arrange
        val expectedGenres = mutableListOf<GenreModel>()
        expectedGenres.add(GenreModel(1, "Name1"))
        expectedGenres.add(GenreModel(2, "Name2"))
        expectedGenres.add(GenreModel(3, "Name3"))
        expectedGenres.add(GenreModel(4, "Name4"))
        val genreString =
            """[{"id":1,"name":"Name1"},{"id":2,"name":"Name2"},{"id":3,"name":"Name3"},{"id":4,"name":"Name4"}]"""
        //Act
        val actual = RoomConverter.stringToGenres(genreString)
        //Assert
        assertEquals(expectedGenres, actual)
    }

    @Test
    fun testStringToGenresEmptyList() {
        // Arrange
        val genreString = "[]"
        //Act
        val actual = RoomConverter.stringToGenres(genreString)
        //Assert
        assertEquals(true, actual.isEmpty())
    }

    @Test
    fun testCastsListToString() {
        // Arrange
        val casts = mutableListOf<CastModel>()
        casts.add(CastModel(1, "charName1", "name1", 1, "image1"))
        casts.add(CastModel(2, "charName2", "name2", 2, "image2"))
        casts.add(CastModel(3, "charName3", "name3", 3, "image3"))
        val expected =
            """[{"cast_id":1,"character":"charName1","name":"name1","order":1,"profile_path":"image1"},{"cast_id":2,"character":"charName2","name":"name2","order":2,"profile_path":"image2"},{"cast_id":3,"character":"charName3","name":"name3","order":3,"profile_path":"image3"}]"""
        //Act
        val actual = RoomConverter.castsToString(casts)
        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun testCastsEmptyListToString() {
        // Arrange
        val casts = mutableListOf<CastModel>()
        val expected = "[]"
        //Act
        val actual = RoomConverter.castsToString(casts)
        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun testStringToCastsList() {
        // Arrange
        val expected = mutableListOf<CastModel>()
        expected.add(CastModel(1, "charName1", "name1", 1, "image1"))
        expected.add(CastModel(2, "charName2", "name2", 2, "image2"))
        expected.add(CastModel(3, "charName3", "name3", 3, "image3"))
        val castStr =
            """[{"cast_id":1,"character":"charName1","name":"name1","order":1,"profile_path":"image1"},{"cast_id":2,"character":"charName2","name":"name2","order":2,"profile_path":"image2"},{"cast_id":3,"character":"charName3","name":"name3","order":3,"profile_path":"image3"}]"""
        //Act
        val actual = RoomConverter.stringToCasts(castStr)
        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun testStringToCastsEmptyList() {
        // Arrange
        val castStr = "[]"
        //Act
        val actual = RoomConverter.stringToCasts(castStr)
        //Assert
        assertEquals(true, actual.isEmpty())
    }

    @Test
    fun testSeasonsListToString() {
        // Arrange
        val seasons = mutableListOf<SeasonModel>()
        seasons.add(
            SeasonModel(
                1,
                "12/03/2017",
                12,
                "seasonName1",
                "This is the overview1",
                "posterPath1",
                1
            )
        )
        seasons.add(
            SeasonModel(
                2,
                "12/05/2018",
                15,
                "seasonName2",
                "This is the overview2",
                "posterPath2",
                2
            )
        )
        seasons.add(
            SeasonModel(
                3,
                "12/02/2019",
                17,
                "seasonName3",
                "This is the overview3",
                "posterPath3",
                3
            )
        )
        val expected =
            """[{"id":1,"airDate":"12/03/2017","episodeCount":12,"name":"seasonName1","overview":"This is the overview1","posterPath":"posterPath1","seasonNumber":1},{"id":2,"airDate":"12/05/2018","episodeCount":15,"name":"seasonName2","overview":"This is the overview2","posterPath":"posterPath2","seasonNumber":2},{"id":3,"airDate":"12/02/2019","episodeCount":17,"name":"seasonName3","overview":"This is the overview3","posterPath":"posterPath3","seasonNumber":3}]""".trimMargin()
        //Act
        val actual = RoomConverter.seasonsToString(seasons)
        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun testSeasonsEmptyListToString() {
        // Arrange
        val seasons = mutableListOf<SeasonModel>()
        val expected = "[]"
        //Act
        val actual = RoomConverter.seasonsToString(seasons)
        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun stringToSeasonsList() {
        // Arrange
        val expected = mutableListOf<SeasonModel>()
        expected.add(
            SeasonModel(
                1,
                "12/03/2017",
                12,
                "seasonName1",
                "This is the overview1",
                "posterPath1",
                1
            )
        )
        expected.add(
            SeasonModel(
                2,
                "12/05/2018",
                15,
                "seasonName2",
                "This is the overview2",
                "posterPath2",
                2
            )
        )
        expected.add(
            SeasonModel(
                3,
                "12/02/2019",
                17,
                "seasonName3",
                "This is the overview3",
                "posterPath3",
                3
            )
        )
        val seasonStr =
            """[{"id":1,"airDate":"12/03/2017","episodeCount":12,"name":"seasonName1","overview":"This is the overview1","posterPath":"posterPath1","seasonNumber":1},{"id":2,"airDate":"12/05/2018","episodeCount":15,"name":"seasonName2","overview":"This is the overview2","posterPath":"posterPath2","seasonNumber":2},{"id":3,"airDate":"12/02/2019","episodeCount":17,"name":"seasonName3","overview":"This is the overview3","posterPath":"posterPath3","seasonNumber":3}]""".trimMargin()
        //Act
        val actual = RoomConverter.stringToSeasons(seasonStr)
        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun stringToSeasonsEmptyList() {
        // Arrange
        val seasonStr = "[]"
        //Act
        val actual = RoomConverter.stringToSeasons(seasonStr)
        //Assert
        assertEquals(true, actual.isEmpty())
    }

    @Test
    fun testEpisodeToString() {
        // Arrange
        val episodeModel =
            EpisodeModel(1, 10, "12/03/2013", "name", 3, "this is the overview", "imagePath", 6.7)
        val expected =
            """{"id":1,"episodeNumber":10,"airDate":"12/03/2013","name":"name","seasonNumber":3,"overview":"this is the overview","stillPath":"imagePath","voteAvg":6.7}"""
        //Act
        val actual = RoomConverter.episodeToString(episodeModel)
        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun stringToEpisode() {
        // Arrange
        val expected =
            EpisodeModel(1, 10, "12/03/2013", "name", 3, "this is the overview", "imagePath", 6.7)
        val episodeStr =
            """{"id":1,"episodeNumber":10,"airDate":"12/03/2013","name":"name","seasonNumber":3,"overview":"this is the overview","stillPath":"imagePath","voteAvg":6.7}"""
        //Act
        val actual = RoomConverter.stringToEpisode(episodeStr)
        //Assert
        assertEquals(expected, actual)
    }
}