package com.sifat.slushflicks.utils

import com.sifat.slushflicks.BuildConfig
import com.sifat.slushflicks.api.ImageDimension

fun getListImageUrl(suffix: String?): String {
    return suffix?.let {
        StringBuilder(BuildConfig.IMAGE_BASE_URL)
            .append(ImageDimension.W342.dimension)
            .append(suffix).toString()
    } ?: EMPTY_STRING
}

fun getCastListImageUrl(suffix: String?): String {
    return suffix?.let {
        StringBuilder(BuildConfig.IMAGE_BASE_URL)
            .append(ImageDimension.W185.dimension)
            .append(suffix).toString()
    } ?: EMPTY_STRING
}