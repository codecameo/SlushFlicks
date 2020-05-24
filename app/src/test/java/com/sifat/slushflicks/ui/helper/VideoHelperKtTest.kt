package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.utils.VIDEO_TYPE_TRAILER
import com.sifat.slushflicks.utils.YOUTUBE_SITE
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

internal class VideoHelperKtTest {

    @Test
    fun testIsYoutubeModelReturnTrue() {
        // Arrange
        val platform = YOUTUBE_SITE
        val type = VIDEO_TYPE_TRAILER
        val videoModel = VideoApiModel("id001", "videokey", type, platform)
        //Act
        val actual = isYoutubeModel(videoApiModel = videoModel)
        //Assert
        assertEquals(true, actual)
    }

    @Test
    fun testVideoOfDiffSiteReturnFalse() {
        // Arrange
        val type = VIDEO_TYPE_TRAILER
        val videoModel = VideoApiModel("id001", "videokey", type, "diff-platform")
        //Act
        val actual = isYoutubeModel(videoApiModel = videoModel)
        //Assert
        assertEquals(false, actual)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Teaser", "Clip", "Featurette", "Behind the Scenes", "Bloopers"])
    fun testVideoTypeReturnFalse(type: String) {
        // Arrange
        val platform = YOUTUBE_SITE
        val videoModel = VideoApiModel("id001", "videokey", type, platform)
        //Act
        val actual = isYoutubeModel(videoApiModel = videoModel)
        //Assert
        assertEquals(false, actual)
    }
}