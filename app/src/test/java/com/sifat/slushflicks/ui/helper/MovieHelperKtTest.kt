package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.api.home.movie.model.MovieApiModel
import com.sifat.slushflicks.api.home.movie.model.MovieListApiModel
import com.sifat.slushflicks.utils.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class MovieHelperKtTest {

    @Test
    fun testMovieListModel() {
        // Arrange
        val apiList = getMovieApiList()
        val genreList = getGenreMap()
        //Act
        val actual = getMovieList(apiList, genreList)
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
    fun testShowMinimalModel() {
        // Arrange
        val movieList = getMovieListModel()
        //Act
        val actual = getMovieMinimalModel(movieList)
        //Assert
        for (index in movieList.indices) {
            assertEquals(movieList[index].id, actual?.get(index)?.id)
            assertEquals(movieList[index].overview, actual?.get(index)?.overview)
            assertEquals(movieList[index].voteAvg, actual?.get(index)?.voteAvg)
            assertEquals(movieList[index].title, actual?.get(index)?.title)
            assertEquals(movieList[index].backdropPath, actual?.get(index)?.backdropPath)
            assertEquals(movieList[index].genres, actual?.get(index)?.genres)
        }
    }

    @Test
    fun testShowMinimalModelWithNullValue() {
        // Arrange
        // Act
        val actual = getMovieMinimalModel(null)
        //Assert
        kotlin.test.assertNull(actual)
    }

    @Test
    fun testTvShowMinimalApiModels() {
        // Arrange
        val movieModel = getMovieApiModel()
        val genreModels = getGenreMap()
        // Act
        val actual = getMovieMinimalApiModel(movieModel, genreModels)
        // Assert
        assertEquals(movieModel.id, actual.id)
        assertEquals(movieModel.title, actual.title)
        assertEquals(movieModel.backdropPath, actual.backdropPath)
        assertEquals(movieModel.overview, actual.overview)
        assertEquals(movieModel.voteAverage, actual.voteAvg)
        for (genreIndex in movieModel.genreIds.indices) {
            assertEquals(
                movieModel.genreIds[genreIndex],
                actual.genres[genreIndex].id
            )
            assertEquals(
                genreModels[movieModel.genreIds[genreIndex]] ?: EMPTY_STRING,
                actual.genres[genreIndex].name
            )
        }
    }

    @Test
    fun testTvShowMinimalApiModelsWithNullValue() {
        // Arrange
        val movieModel = getMovieApiNullModel()
        val genreModels = getGenreMap()
        // Act
        val actual = getMovieMinimalApiModel(movieModel, genreModels)
        // Assert
        assertEquals(movieModel.id, actual.id)
        assertEquals(movieModel.title, actual.title)
        assertEquals(movieModel.backdropPath ?: EMPTY_STRING, actual.backdropPath)
        assertEquals(movieModel.overview, actual.overview)
        assertEquals(movieModel.voteAverage, actual.voteAvg)
        for (genreIndex in movieModel.genreIds.indices) {
            assertEquals(
                movieModel.genreIds[genreIndex],
                actual.genres[genreIndex].id
            )
            assertEquals(
                genreModels[movieModel.genreIds[genreIndex]] ?: EMPTY_STRING,
                actual.genres[genreIndex].name
            )
        }
    }

    @Test
    fun testMetaData() {
        // Arrange
        val movieListApiModel = MovieListApiModel(
            3,
            5,
            46,
            mutableListOf()
        )
        //Act
        val metadata = getMetaData(movieListApiModel)
        //Assert
        assertEquals(movieListApiModel.page, metadata?.page)
        assertEquals(movieListApiModel.totalPages, metadata?.totalPage)
        assertEquals(movieListApiModel.totalResults, metadata?.totalResult)
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
    fun testCollectionModelsWithInitialPage() {
        // Arrange
        val tvList = getMovieListModel()
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
        val movieList = getMovieListModel()
        val collectionName = "collectionName"
        val page = 13
        //Act
        val actualList = getCollectionModels(movieList, collectionName, page)
        //Assert
        for (index in movieList.indices) {
            assertEquals(movieList[index].id, actualList[index].id)
            assertEquals(collectionName, actualList[index].collection)
            assertEquals(((page - 1) * PAGE_SIZE + index), actualList[index].index)
        }
    }

    @Test
    fun testCollectionModelsWithInvalidPage() {
        // Arrange
        val movieList = getMovieListModel()
        val collectionName = "collectionName"
        val page = -2
        //Assert
        val exception = assertThrows(RuntimeException::class.java) {
            //Act
            getCollectionModels(movieList, collectionName, page)
        }
        assertEquals(String.format(INVALID_PAGE, page), exception.message)
    }

    @Test
    fun testMovieDetailsApiModel() {
        // Arrange
        val movieDetailApiModel = getMovieDetailsTestModel()
        //Act
        val actual = getMovieDetails(movieDetailApiModel)
        //Assert
        assertEquals(movieDetailApiModel.id, actual?.id)
        assertEquals(movieDetailApiModel.title, actual?.title)
        assertEquals(movieDetailApiModel.voteAverage, actual?.voteAvg)
        assertEquals(movieDetailApiModel.voteCount, actual?.voteCount)
        assertEquals(movieDetailApiModel.popularity, actual?.popularity)
        assertEquals(movieDetailApiModel.posterPath, actual?.posterPath)
        assertEquals(movieDetailApiModel.backdropPath, actual?.backdropPath)
        assertEquals(movieDetailApiModel.overview, actual?.overview)
        assertEquals(movieDetailApiModel.status, actual?.status)
        assertEquals(movieDetailApiModel.releaseDate, actual?.releaseData)
        assertEquals(movieDetailApiModel.genres, actual?.genres)
        assertEquals(movieDetailApiModel.tagline, actual?.tagline)
        assertEquals(movieDetailApiModel.runtime, actual?.runtime)
        assertEquals(movieDetailApiModel.budget, actual?.budget)
        assertEquals(movieDetailApiModel.revenue, actual?.revenue)
    }

    @Test
    fun testMovieDetailsApiModelWithNullValue() {
        // Arrange
        val movieDetailApiModel = getNullMovieDetailsTestModel()
        //Act
        val actual = getMovieDetails(movieDetailApiModel)
        //Assert
        assertEquals(movieDetailApiModel.id, actual?.id)
        assertEquals(movieDetailApiModel.title, actual?.title)
        assertEquals(movieDetailApiModel.voteAverage, actual?.voteAvg)
        assertEquals(movieDetailApiModel.voteCount, actual?.voteCount)
        assertEquals(movieDetailApiModel.popularity, actual?.popularity)
        assertEquals(movieDetailApiModel.overview, actual?.overview)
        assertEquals(movieDetailApiModel.status, actual?.status)
        assertEquals(movieDetailApiModel.genres, actual?.genres)
        assertEquals(movieDetailApiModel.tagline, actual?.tagline)
        assertEquals(movieDetailApiModel.runtime, actual?.runtime)
        assertEquals(movieDetailApiModel.budget, actual?.budget)
        assertEquals(movieDetailApiModel.revenue, actual?.revenue)
        assertEquals(movieDetailApiModel.releaseDate ?: EMPTY_STRING, actual?.releaseData)
        assertEquals(movieDetailApiModel.posterPath ?: EMPTY_STRING, actual?.posterPath)
        assertEquals(movieDetailApiModel.backdropPath ?: EMPTY_STRING, actual?.backdropPath)
    }

    private fun getMovieApiList(): List<MovieApiModel> {
        val list = mutableListOf<MovieApiModel>()
        var item = MovieApiModel(
            1,
            23,
            6.7,
            "title1",
            "12/03/2017",
            "backdropPath1",
            "overview1",
            "posterPath1",
            67.9,
            mutableListOf<Long>(2, 3, 5)
        )
        list.add(item)

        item = MovieApiModel(
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
        // checking null values
        item = MovieApiModel(
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

    private fun getMovieApiModel() = MovieApiModel(
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

    private fun getMovieApiNullModel() = MovieApiModel(
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
}