package com.example.slushflicks.model

import com.example.slushflicks.utils.EMPTY_STRING
import com.example.slushflicks.utils.INVALID_ID

data class GenreModel(val id: Long = INVALID_ID.toLong(), val name: String = EMPTY_STRING)