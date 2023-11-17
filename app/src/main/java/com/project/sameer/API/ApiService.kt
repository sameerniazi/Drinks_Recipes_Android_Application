package com.project.sameer.API


import com.project.sameer.DataClass.DrinkData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("search.php")
     fun searchCocktail(@Query("s") query: String): Call<DrinkData>
     @GET("search.php")
     fun searchByAlpha(@Query("f") query: String): Call<DrinkData>
}