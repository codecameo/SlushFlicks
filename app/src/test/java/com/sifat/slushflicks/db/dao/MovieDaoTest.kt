package com.sifat.slushflicks.db.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sifat.slushflicks.db.AppDatabaseTest
import com.sifat.slushflicks.model.CastModel
import com.sifat.slushflicks.ui.helper.getMovieDetails
import com.sifat.slushflicks.utils.getMovieDetailsTestModel
import com.sifat.slushflicks.utils.getMovieDiffDetailsTestModel
import com.sifat.slushflicks.utils.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class MovieDaoTest : AppDatabaseTest() {

    lateinit var movieDaoTest: MovieDao

    @Before
    override fun setup() {
        super.setup()
        movieDaoTest = appDatabase.getMovieDao()
    }

    @Test
    fun testLiveMovieDetails() = mainCoroutineDispatcher.runBlockingTest {
        val expected = getMovieDetails(getMovieDetailsTestModel())
        movieDaoTest.insertReplace(expected!!)
        val actual = movieDaoTest.listenToMovie(expected.id).getOrAwaitValue()
        assertEquals(expected, actual)
    }

    @Test
    fun testLiveMovieDetailsWithInvalidId() = mainCoroutineDispatcher.runBlockingTest {
        val actual = movieDaoTest.listenToMovie(-1).getOrAwaitValue()
        assertNull(actual)
    }

    @Test
    fun testMovieId() = mainCoroutineDispatcher.runBlockingTest {
        val movie = getMovieDetails(getMovieDetailsTestModel())
        val expected = movie!!.id
        movieDaoTest.insertReplace(movie)
        val actual = movieDaoTest.getMovieId(movie.id)
        assertEquals(expected, actual)
    }

    @Test
    fun testMovieIdWithInvalidId() = mainCoroutineDispatcher.runBlockingTest {
        val actual = movieDaoTest.getMovieId(-1)
        assertNull(actual)
    }

    @Test
    fun testUpdateVideo() = mainCoroutineDispatcher.runBlockingTest {
        val movie = getMovieDetails(getMovieDetailsTestModel())
        val videoUrl = "videoUrl"
        movieDaoTest.insertReplace(movie!!)
        movieDaoTest.update(movieId = movie.id, key = videoUrl)
        val actual = movieDaoTest.getMovie(movieId = movie.id).video
        assertEquals(videoUrl, actual)
    }

    @Test
    fun testUpdateCast() = mainCoroutineDispatcher.runBlockingTest {
        val movie = getMovieDetails(getMovieDetailsTestModel())
        val cast = mutableListOf<CastModel>()
        cast.add(CastModel(12, "iron", "downey", 2, "profileImage1"))
        cast.add(CastModel(22, "captain", "evans", 1, "profileImage1"))
        cast.add(CastModel(56, "thor", "crish", 4, "profileImage1"))

        movieDaoTest.insertReplace(movie!!)
        movieDaoTest.update(movieId = movie.id, casts = cast)
        val actual = movieDaoTest.getMovie(movieId = movie.id).casts
        assertEquals(cast, actual)
    }

    @Test
    fun testUpdateEmptyCast() = mainCoroutineDispatcher.runBlockingTest {
        val movie = getMovieDetails(getMovieDetailsTestModel())
        val cast = emptyList<CastModel>()
        movieDaoTest.insertReplace(movie!!)
        movieDaoTest.update(movieId = movie.id, casts = cast)
        val actual = movieDaoTest.getMovie(movieId = movie.id).casts
        assertEquals(cast, actual)
    }

    @Test
    fun testUpdateMovie() = mainCoroutineDispatcher.runBlockingTest {
        val movie = getMovieDetails(getMovieDetailsTestModel())
        val expected = getMovieDetails(getMovieDiffDetailsTestModel())
        movieDaoTest.insertReplace(movie!!)
        movieDaoTest.update(
            id = expected!!.id,
            voteAvg = expected!!.voteAvg,
            voteCount = expected.voteCount,
            releaseData = expected.releaseData,
            status = expected.status,
            revenue = expected.revenue,
            budget = expected.budget,
            popularity = expected.popularity,
            tagline = expected.tagline,
            genres = expected.genres,
            runtime = expected.runtime
        )
        val actual = movieDaoTest.getMovie(movieId = movie.id)
        assertEquals(expected, actual)
    }
}