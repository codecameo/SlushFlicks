package com.example.slushflicks.utils

import com.example.slushflicks.BuildConfig
import com.example.slushflicks.api.ImageDimension

fun getListImageUrl(suffix: String?): String {
    return suffix?.let {
        StringBuilder(BuildConfig.IMAGE_BASE_URL)
            .append(ImageDimension.W342.dimension)
            .append(suffix).toString()
    } ?: EMPTY_STRING
}