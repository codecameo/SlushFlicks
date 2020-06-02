package com.sifat.slushflicks.db.dao

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.sifat.slushflicks.db.AppDatabaseTest
import com.sifat.slushflicks.model.CastModel
import com.sifat.slushflicks.ui.helper.getTvDetails
import com.sifat.slushflicks.utils.getOrAwaitValue
import com.sifat.slushflicks.utils.getTvDetailsTestModel
import com.sifat.slushflicks.utils.getTvDiffDetailsTestModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import kotlin.test.assertEquals
import kotlin.test.assertNull

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class TvDaoTest : AppDatabaseTest() {

    private lateinit var tvDaoTest: TvDao

    @Before
    override fun setup() {
        super.setup()
        tvDaoTest = appDatabase.getTvDao()
    }

    @Test
    fun testLiveTvDetails() = mainCoroutineDispatcher.runBlockingTest {
        val expected = getTvDetails(getTvDetailsTestModel())
        tvDaoTest.insertReplace(expected!!)
        val actual = tvDaoTest.listenToTvShow(expected.id).getOrAwaitValue()
        assertEquals(expected, actual)
    }

    @Test
    fun testLiveTvDetailsWithInvalidId() = mainCoroutineDispatcher.runBlockingTest {
        val actual = tvDaoTest.listenToTvShow(-1).getOrAwaitValue()
        assertNull(actual)
    }

    @Test
    fun testTvId() = mainCoroutineDispatcher.runBlockingTest {
        val tv = getTvDetails(getTvDetailsTestModel())
        val expected = tv!!.id
        tvDaoTest.insertReplace(tv)
        val actual = tvDaoTest.getTvShowId(tv.id)
        assertEquals(expected, actual)
    }

    @Test
    fun testTvIdWithInvalidId() = mainCoroutineDispatcher.runBlockingTest {
        val actual = tvDaoTest.getTvShowId(-1)
        assertNull(actual)
    }

    @Test
    fun testUpdateVideo() = mainCoroutineDispatcher.runBlockingTest {
        val tv = getTvDetails(getTvDetailsTestModel())
        val videoUrl = "videoUrl"
        tvDaoTest.insertReplace(tv!!)
        tvDaoTest.update(tvShowId = tv.id, key = videoUrl)
        val actual = tvDaoTest.getTvShow(tvShowId = tv.id).video
        assertEquals(videoUrl, actual)
    }

    @Test
    fun testUpdateCast() = mainCoroutineDispatcher.runBlockingTest {
        val tv = getTvDetails(getTvDetailsTestModel())
        val cast = mutableListOf<CastModel>()
        cast.add(CastModel(12, "iron", "downey", 2, "profileImage1"))
        cast.add(CastModel(22, "captain", "evans", 1, "profileImage1"))
        cast.add(CastModel(56, "thor", "crish", 4, "profileImage1"))

        tvDaoTest.insertReplace(tv!!)
        tvDaoTest.update(tvShowId = tv.id, casts = cast)
        val actual = tvDaoTest.getTvShow(tvShowId = tv.id).casts
        assertEquals(cast, actual)
    }

    @Test
    fun testUpdateEmptyCast() = mainCoroutineDispatcher.runBlockingTest {
        val tv = getTvDetails(getTvDetailsTestModel())
        val cast = emptyList<CastModel>()
        tvDaoTest.insertReplace(tv!!)
        tvDaoTest.update(tvShowId = tv.id, casts = cast)
        val actual = tvDaoTest.getTvShow(tvShowId = tv.id).casts
        assertEquals(cast, actual)
    }

    @Test
    fun testUpdateTv() = mainCoroutineDispatcher.runBlockingTest {
        val tv = getTvDetails(getTvDetailsTestModel())
        val expected = getTvDetails(getTvDiffDetailsTestModel())
        tvDaoTest.insertReplace(tv!!)
        tvDaoTest.update(
            id = expected!!.id,
            voteAvg = expected.voteAvg,
            voteCount = expected.voteCount,
            releaseData = expected.releaseData,
            status = expected.status,
            popularity = expected.popularity,
            genres = expected.genres,
            runtime = expected.runtime,
            numOfEpisode = expected.numOfEpisode,
            numOfSeason = expected.numOfSeason,
            seasons = expected.seasons,
            directors = expected.directors,
            nextEpisode = expected.nextEpisode,
            lastEpisode = expected.lastEpisode
        )
        val actual = tvDaoTest.getTvShow(tvShowId = tv.id)
        assertEquals(expected, actual)
    }
}