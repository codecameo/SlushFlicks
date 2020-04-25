package com.example.slushflicks.api

data class ApiError(val statusCode : Int, val apiTag : String, val errorMessage : String)