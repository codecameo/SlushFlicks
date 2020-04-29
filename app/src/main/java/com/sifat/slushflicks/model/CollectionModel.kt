package com.sifat.slushflicks.model

import com.sifat.slushflicks.utils.EMPTY_STRING

data class CollectionModel(
    var name: String = EMPTY_STRING,
    var label: String = EMPTY_STRING,
    var isEnable: Boolean = false
)