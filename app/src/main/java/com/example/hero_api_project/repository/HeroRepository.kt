package com.example.hero_api_project.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.example.hero_api_project.database.HeroDao
import com.example.hero_api_project.database.HeroItem
import com.example.hero_api_project.model.HeroModel
import com.example.hero_api_project.retrofit.RestAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HeroRepository(private val heroDao: HeroDao) {

    val TAG = HeroRepository::class.java.simpleName

    fun getAllHeroes() : LiveData<List<HeroModel>> {
        return heroDao.getAllHeroes()
    }

    fun ApiCallAndStoreInDB() {
        var rf = Retrofit.Builder()
            .baseUrl(RestAPI.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var restAPI = rf.create(RestAPI::class.java)
        restAPI.getAllHeroes().enqueue(object : Callback<List<HeroModel>> {
            override fun onResponse(
                call: Call<List<HeroModel>>,
                response: Response<List<HeroModel>>
            ) {
                Log.d("response", response.body().toString())
                when(response.code()) {
                    200 -> {
                        Thread(Runnable {
                            heroDao.deleteAll()
                            heroDao.insert(response.body()!!)
                        }).start()
                    }
                }
            }

            override fun onFailure(call: Call<List<HeroModel>>, t: Throwable) {
                Log.e(TAG, "OOPS! Something went wrong!")
            }

        })
    }
}