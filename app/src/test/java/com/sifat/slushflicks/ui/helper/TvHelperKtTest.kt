package com.sifat.slushflicks.ui.helper

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.sifat.slushflicks.api.home.tv.model.*
import com.sifat.slushflicks.model.GenreModel
import com.sifat.slushflicks.model.SeasonModel
import com.sifat.slushflicks.model.TvModel
import com.sifat.slushflicks.utils.*
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test
import kotlin.test.assertNotEquals
import kotlin.test.assertNull

internal class TvHelperKtTest {

    @Test
    fun testTvListModel() {
        // Arrange
        val apiList = getTvApiList()
        val genreList = getGenreList()
        //Act
        val actual = getTvList(apiList, genreList)
        //Assert
        for (index in actual.indices) {
            assertEquals(apiList[index].id, actual[index].id)
            assertEquals(apiList[index].title, actual[index].title)
            assertEquals(apiList[index].voteAverage, actual[index].voteAvg)
            assertEquals(apiList[index].voteCount, actual[index].voteCount)
            assertEquals(apiList[index].overview, actual[index].overview)
            assertEquals(apiList[index].popularity, actual[index].popularity)
            assertEquals(apiList[index].backdropPath ?: EMPTY_STRING, actual[index].backdropPath)
            assertEquals(apiList[index].posterPath ?: EMPTY_STRING, actual[index].posterPath)
            assertEquals(apiList[index].releaseDate ?: EMPTY_STRING, actual[index].releaseData)
            for (genreIndex in apiList[index].genreIds.indices) {
                assertEquals(
                    apiList[index].genreIds[genreIndex],
                    actual[index].genres[genreIndex].id
                )
                assertEquals(
                    genreList[apiList[index].genreIds[genreIndex]] ?: EMPTY_STRING,
                    actual[index].genres[genreIndex].name
                )
            }
        }
    }

    @Test
    fun testCollectionModelsWithInitialPage() {
        // Arrange
        val tvList = getTvListModel()
        val collectionName = "collectionName"
        val page = 1
        //Act
        val actualList = getCollectionModels(tvList, collectionName, page)
        //Assert
        for (index in tvList.indices) {
            assertEquals(tvList[index].id, actualList[index].id)
            assertEquals(collectionName, actualList[index].collection)
            assertEquals(index, actualList[index].index)
        }
    }

    @Test
    fun testCollectionModelsWithLaterPage() {
        // Arrange
        val tvList = getTvListModel()
        val collectionName = "collectionName"
        val page = 13
        //Act
        val actualList = getCollectionModels(tvList, collectionName, page)
        //Assert
        for (index in tvList.indices) {
            assertEquals(tvList[index].id, actualList[index].id)
            assertEquals(collectionName, actualList[index].collection)
            assertEquals(((page - 1) * PAGE_SIZE + index), actualList[index].index)
        }
    }

    @Test
    fun testCollectionModelsWithInvalidPage() {
        // Arrange
        val tvList = getTvListModel()
        val collectionName = "collectionName"
        val page = -2
        //Assert
        val exception = assertThrows(RuntimeException::class.java) {
            //Act
            getCollectionModels(tvList, collectionName, page)
        }
        assertEquals(String.format(INVALID_PAGE, page), exception.message)
    }

    @Test
    fun testMetaData() {
        // Arrange
        val tvListApiModel = TvListApiModel(
            3,
            5,
            46,
            mutableListOf()
        )
        //Act
        val metadata = getMetaData(tvListApiModel)
        //Assert
        assertEquals(tvListApiModel.page, metadata?.page)
        assertEquals(tvListApiModel.totalPages, metadata?.totalPage)
        assertEquals(tvListApiModel.totalResults, metadata?.totalResult)
    }

    @Test
    fun testMetaDataWithNullValue() {
        // Arrange

        //Act
        val metadata = getMetaData(tvListApiModel = null)
        //Assert
        assertNull(metadata)
    }

    @Test
    fun testShowMinimalModel() {
        // Arrange
        val tvList = getTvListModel()
        //Act
        val actual = getTvMinimalModel(tvList)
        //Assert
        for (index in tvList.indices) {
            assertEquals(tvList[index].id, actual?.get(index)?.id)
            assertEquals(tvList[index].overview, actual?.get(index)?.overview)
            assertEquals(tvList[index].voteAvg, actual?.get(index)?.voteAvg)
            assertEquals(tvList[index].title, actual?.get(index)?.title)
            assertEquals(tvList[index].backdropPath, actual?.get(index)?.backdropPath)
            assertEquals(tvList[index].genres, actual?.get(index)?.genres)
        }
    }

    @Test
    fun testShowMinimalModelWithNullValue() {
        // Arrange
        // Act
        val actual = getTvMinimalModel(null)
        //Assert
        assertNull(actual)
    }

    @Test
    fun testTvShowMinimalApiModels() {
        // Arrange
        val tvModel = getTvApiModel()
        val genreModels = getGenreList()
        // Act
        val actual = getTvShowMinimalApiModel(tvModel, genreModels)
        // Assert
        assertEquals(tvModel.id, actual.id)
        assertEquals(tvModel.title, actual.title)
        assertEquals(tvModel.backdropPath, actual.backdropPath)
        assertEquals(tvModel.overview, actual.overview)
        assertEquals(tvModel.voteAverage, actual.voteAvg)
        for (genreIndex in tvModel.genreIds.indices) {
            assertEquals(
                tvModel.genreIds[genreIndex],
                actual.genres[genreIndex].id
            )
            assertEquals(
                genreModels[tvModel.genreIds[genreIndex]] ?: EMPTY_STRING,
                actual.genres[genreIndex].name
            )
        }
    }


    @Test
    fun testTvShowMinimalApiModelsWithNullValue() {
        // Arrange
        val tvModel = getTvApiNullModel()
        val genreModels = getGenreList()
        // Act
        val actual = getTvShowMinimalApiModel(tvModel, genreModels)
        // Assert
        assertEquals(tvModel.id, actual.id)
        assertEquals(tvModel.title, actual.title)
        assertEquals(tvModel.backdropPath ?: EMPTY_STRING, actual.backdropPath)
        assertEquals(tvModel.overview, actual.overview)
        assertEquals(tvModel.voteAverage, actual.voteAvg)
        for (genreIndex in tvModel.genreIds.indices) {
            assertEquals(
                tvModel.genreIds[genreIndex],
                actual.genres[genreIndex].id
            )
            assertEquals(
                genreModels[tvModel.genreIds[genreIndex]] ?: EMPTY_STRING,
                actual.genres[genreIndex].name
            )
        }
    }

    @Test
    fun testTvDetailsApiModel() {
        // Arrange
        val tvDetailApiModel = getTvDetailsTestModel()
        //Act
        val actual = getTvDetails(tvDetailApiModel)
        //Assert
        assertEquals(tvDetailApiModel.id, actual?.id)
        assertEquals(tvDetailApiModel.name, actual?.title)
        assertEquals(tvDetailApiModel.voteAvg, actual?.voteAvg)
        assertEquals(tvDetailApiModel.voteCount, actual?.voteCount)
        assertEquals(tvDetailApiModel.popularity, actual?.popularity)
        assertEquals(tvDetailApiModel.posterPath, actual?.posterPath)
        assertEquals(tvDetailApiModel.backdropPath, actual?.backdropPath)
        assertEquals(tvDetailApiModel.overview, actual?.overview)
        assertEquals(tvDetailApiModel.episodeCount, actual?.numOfEpisode)
        assertEquals(tvDetailApiModel.seasonCount, actual?.numOfSeason)
        assertEquals(tvDetailApiModel.status, actual?.status)
        assertEquals(tvDetailApiModel.firstAirDate, actual?.releaseData)
        assertEquals(tvDetailApiModel.genres, actual?.genres)
    }

    @Test
    fun testTvDetailsApiModelWithNullValue() {
        // Arrange
        val tvDetailApiModel = getNullTvDetailsTestModel()
        //Act
        val actual = getTvDetails(tvDetailApiModel)
        //Assert
        assertEquals(tvDetailApiModel.id, actual?.id)
        assertEquals(tvDetailApiModel.name, actual?.title)
        assertEquals(tvDetailApiModel.voteAvg, actual?.voteAvg)
        assertEquals(tvDetailApiModel.voteCount, actual?.voteCount)
        assertEquals(tvDetailApiModel.popularity, actual?.popularity)
        assertEquals(tvDetailApiModel.overview, actual?.overview)
        assertEquals(tvDetailApiModel.episodeCount, actual?.numOfEpisode)
        assertEquals(tvDetailApiModel.seasonCount, actual?.numOfSeason)
        assertEquals(tvDetailApiModel.status, actual?.status)
        assertEquals(tvDetailApiModel.genres, actual?.genres)
        assertEquals(tvDetailApiModel.firstAirDate ?: EMPTY_STRING, actual?.releaseData)
        assertEquals(tvDetailApiModel.posterPath ?: EMPTY_STRING, actual?.posterPath)
        assertEquals(tvDetailApiModel.backdropPath ?: EMPTY_STRING, actual?.backdropPath)
    }

    @Test
    fun testDirectors() {
        // Arrange
        val createdBy =
            mutableListOf<CreatedBy>(CreatedBy("thomas"), CreatedBy("zim"), CreatedBy("oshan"))
        val expected = "thomas • zim • oshan"
        //Act
        val actual = getDirectors(createdBy)
        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun testDirectorsEmpty() {
        // Arrange
        val createdBy = emptyList<CreatedBy>()
        val expected = ""
        //Act
        val actual = getDirectors(createdBy)
        //Assert
        assertEquals(expected, actual)
    }

    @Test
    fun testRuntime() {
        // Arrange
        val runtime = mutableListOf<Int>(67, 43, 78, 75)
        val expected = 65
        //Act
        val actualRuntime = getRuntime(runtime)
        //Assert
        assertEquals(expected, actualRuntime)
    }

    @Test
    fun testEmptyRuntime() {
        // Arrange
        val runtime = emptyList<Int>()
        val expected = 0
        //Act
        val actualRuntime = getRuntime(runtime)
        //Assert
        assertEquals(expected, actualRuntime)
    }

    @Test
    fun testEpisode() {
        // Arrange
        val episode = Episode(
            1,
            "stillPath",
            "name",
            9,
            "21/02/2019",
            "overview",
            3,
            5.6
        )
        //Act
        val actual = getEpisode(episode)
        //Assert
        assertEquals(episode.episodeNumber, actual?.episodeNumber)
        assertEquals(episode.overview, actual?.overview)
        assertEquals(episode.stillPath, actual?.stillPath)
        assertEquals(episode.seasonNumber, actual?.seasonNumber)
        assertEquals(episode.airDate, actual?.airDate)
        assertEquals(episode.voteAvg, actual?.voteAvg)
        assertEquals(episode.name, actual?.name)
    }

    @Test
    fun testEpisodeWithNullFieldValue() {
        // Arrange
        val episode = Episode(
            1,
            null,
            "name",
            9,
            null,
            "overview",
            3,
            5.6
        )
        //Act
        val actual = getEpisode(episode)
        //Assert
        assertEquals(episode.episodeNumber, actual?.episodeNumber)
        assertEquals(episode.overview, actual?.overview)
        assertEquals(episode.stillPath ?: EMPTY_STRING, actual?.stillPath)
        assertEquals(episode.seasonNumber, actual?.seasonNumber)
        assertEquals(episode.airDate ?: EMPTY_STRING, actual?.airDate)
        assertEquals(episode.voteAvg, actual?.voteAvg)
        assertEquals(episode.name, actual?.name)
    }

    @Test
    fun testEpisodeWithNullValue() {
        // Arrange
        //Act
        val actual = getEpisode(null)
        //Assert
        assertNull(actual)
    }

    @Test
    fun testSeasonsList() {
        // Arrange
        val token = object : TypeToken<List<Season>>() {}.type
        val seasons = Gson().fromJson<List<Season>>(seasonListResponse, token)
        //Act
        val actualSeason = getSeasons(seasons)
        //Assert
        var offset = 0
        for (index in seasons.indices) {
            // test discarding season 0
            if (seasons[index].seasonNumber == 0) {
                assertNotEquals(seasons[index].id, actualSeason[index - offset].id)
                offset = 1
                continue
            }
            assertEquals(seasons[index].airDate, actualSeason[index - offset].airDate)
            assertEquals(seasons[index].episodeCount, actualSeason[index - offset].episodeCount)
            assertEquals(seasons[index].id, actualSeason[index - offset].id)
            assertEquals(seasons[index].name, actualSeason[index - offset].name)
            assertEquals(seasons[index].overview, actualSeason[index - offset].overview)
            assertEquals(seasons[index].posterPath, actualSeason[index - offset].posterPath)
            assertEquals(seasons[index].seasonNumber, actualSeason[index - offset].seasonNumber)
        }
    }

    @Test
    fun testSeasonsListWithNull() {
        // Arrange
        //Act
        val actualSeason = getSeasons(null)
        //Assert
        assertEquals(emptyList<SeasonModel>(), actualSeason)
    }

    @Test
    fun testSeason() {
        // Arrange
        val season = Gson().fromJson(seasonResponse, Season::class.java)
        //Act
        val actualSeason = getSeason(season)
        //Assert
        assertEquals(season.seasonNumber, actualSeason.seasonNumber)
        assertEquals(season.id, actualSeason.id)
        assertEquals(season.name, actualSeason.name)
        assertEquals(season.episodeCount, actualSeason.episodeCount)
        assertEquals(season.posterPath, actualSeason.posterPath)
        assertEquals(season.overview, actualSeason.overview)
        assertEquals(season.airDate, actualSeason.airDate)
    }

    @Test
    fun testSeasonWithNullValue() {
        // Arrange
        val season = Gson().fromJson(seasonNullResponse, Season::class.java)
        //Act
        val actualSeason = getSeason(season)
        //Assert
        assertEquals(season.seasonNumber, actualSeason.seasonNumber)
        assertEquals(season.id, actualSeason.id)
        assertEquals(season.name, actualSeason.name)
        assertEquals(season.episodeCount, actualSeason.episodeCount)
        assertEquals(season.posterPath ?: EMPTY_STRING, actualSeason.posterPath)
        assertEquals(season.overview, actualSeason.overview)
        assertEquals(season.airDate ?: EMPTY_STRING, actualSeason.airDate)
    }

    private fun getTvApiList(): List<TvApiModel> {
        val list = mutableListOf<TvApiModel>()
        var item = TvApiModel(
            1,
            23,
            6.7,
            "title1",
            "12/03/2017",
            "backDropPath1",
            "overview1",
            "posterPath1",
            45.7,
            mutableListOf<Long>(2, 3, 5)
        )
        list.add(item)

        item = TvApiModel(
            2,
            33,
            7.7,
            "title2",
            "22/05/2007",
            "backDropPath2",
            "overview2",
            "posterPath2",
            85.7,
            mutableListOf<Long>(1, 3, 4)
        )
        list.add(item)

        item = TvApiModel(
            3,
            123,
            9.7,
            "title3",
            "02/12/2013",
            "backDropPath3",
            "overview3",
            "posterPath3",
            75.3,
            mutableListOf<Long>(1, 3)
        )
        list.add(item)
        // checking null values
        item = TvApiModel(
            4,
            52,
            7.3,
            "title4",
            null,
            null,
            "overview4",
            null,
            85.3,
            mutableListOf<Long>(4, 3, 9)
        )
        list.add(item)
        return list
    }

    private fun getTvListModel(): List<TvModel> {
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

    private fun getTvApiModel() = TvApiModel(
        id = 1,
        overview = "overview",
        backdropPath = "backdropPath",
        title = "title",
        voteAverage = 5.8,
        genreIds = mutableListOf(1, 4, 3),
        popularity = 78.9,
        posterPath = "posterPath",
        releaseDate = "23/04/2018",
        voteCount = 462
    )

    private fun getTvApiNullModel() = TvApiModel(
        id = 1,
        overview = "overview",
        backdropPath = null,
        title = "title",
        voteAverage = 5.8,
        genreIds = mutableListOf(1, 7, 3),
        popularity = 78.9,
        posterPath = null,
        releaseDate = null,
        voteCount = 462
    )

    private val seasonListResponse by lazy {
        """
            [{
            "air_date": "2018-04-24",
            "episode_count": 5,
            "id": 120893,
            "name": "Season 1",
            "overview": "overview1",
            "poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
            "season_number": 1
            },
            {
            "air_date": "2019-04-24",
            "episode_count": 6,
            "id": 120462,
            "name": "Season 0",
            "overview": "overview2",
            "poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
            "season_number": 0
            },
            {
            "air_date": "2020-04-24",
            "episode_count": 8,
            "id": 120864,
            "name": "Season 3",
            "overview": "",
            "poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
            "season_number": 3
            }
            ]
        """.trimIndent()
    }

    private val seasonResponse by lazy {
        """
            {
            "air_date": "2020-04-24",
            "episode_count": 5,
            "id": 120893,
            "name": "Season 1",
            "overview": "",
            "poster_path": "/JTmkzwSDRiS9nvSPwpDB9fmZj2.jpg",
            "season_number": 1
            }
        """.trimIndent()
    }

    private val seasonNullResponse by lazy {
        """
            {
            "air_date": null,
            "episode_count": 5,
            "id": 120893,
            "name": "Season 1",
            "overview": "",
            "poster_path": null,
            "season_number": 1
            }
        """.trimIndent()
    }
}