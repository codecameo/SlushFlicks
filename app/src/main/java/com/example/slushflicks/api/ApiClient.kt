package com.example.slushflicks.api

import com.example.slushflicks.BuildConfig
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient  {
    var retrofitDataClient: Retrofit? = null
    private set
    private val gson: Gson

    init {
        gson = setGson()
        retrofitDataClient = setDataClient()
    }

    private fun setGson(): Gson {
        return GsonBuilder()
            .setLenient()
            .create()
    }

    private fun setDataClient(): Retrofit {
        retrofitDataClient?.apply {
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL + BuildConfig.API_VERSION)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        }

        return retrofitDataClient!!
    }
}