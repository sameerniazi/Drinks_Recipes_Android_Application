package com.project.sameer.API

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object DrinkInstance {
    private val retrofit: Retrofit =
        Retrofit.Builder()
            .baseUrl("https://www.thecocktaildb.com/api/json/v1/1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

     val api: ApiService = retrofit.create(ApiService::class.java)
}