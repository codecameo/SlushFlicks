package com.sifat.slushflicks.ui.helper

import com.sifat.slushflicks.api.details.model.VideoApiModel
import com.sifat.slushflicks.utils.VIDEO_TYPE_TRAILER
import com.sifat.slushflicks.utils.YOUTUBE_SITE

fun isYoutubeModel(videoApiModel: VideoApiModel): Boolean {
    return videoApiModel.run {
        site == YOUTUBE_SITE && type == VIDEO_TYPE_TRAILER
    }
}