package com.sifat.slushflicks.utils

fun getGenreList(): Map<Long, String> {
    val map = HashMap<Long, String>()
    map[1] = "genre1"
    map[2] = "genre2"
    map[3] = "genre3"
    map[4] = "genre4"
    map[5] = "genre5"
    return map
}