package com.sifat.slushflicks.model

data class CastModel(
    val castId: Int,
    val character: String,
    val name: String,
    val order: Int,
    val profileImage: String
)

/**
"cast_id": 34,
"character": "Thomas A. Anderson / Neo",
"credit_id": "52fe425bc3a36847f80181c1",
"gender": 2,
"id": 6384,
"name": "Keanu Reeves",
"order": 0,
"profile_path": "/bOlYWhVuOiU6azC4Bw6zlXZ5QTC.jpg"
 * */