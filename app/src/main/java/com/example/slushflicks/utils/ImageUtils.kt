package com.example.slushflicks.utils

import com.example.slushflicks.BuildConfig
import com.example.slushflicks.api.ImageDimension
import java.lang.StringBuilder

fun getListImageUrl(suffix: String): String {
    return StringBuilder(BuildConfig.IMAGE_BASE_URL)
        .append(ImageDimension.W342.dimension)
        .append(suffix).toString()
}