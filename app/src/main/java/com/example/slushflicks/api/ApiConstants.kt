package com.example.slushflicks.api

enum class ImageDimension(val dimension : String) {
    // "w92", "w154", "w185", "w342", "w500", "w780", or "original"
    W92("/w92"),
    W154("/w154"),
    W185("/w185"),
    W342("/w342"),
    W500("/w500"),
    W780("/w780"),
    Original("/original")
}

const val KEY_API_KEY = "api_key"
const val KEY_TIME_WINDOW = "time_window"