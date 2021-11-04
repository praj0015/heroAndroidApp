package com.example.hero_api_project.retrofit

import com.example.hero_api_project.model.HeroModel
import retrofit2.Call
import retrofit2.http.GET

interface RestAPI {
    @GET("/api/heroStats")
    fun getAllHeroes() : Call<List<HeroModel>>
    companion object{
        const val BASE_URL = "https://api.opendota.com"
    }
}
