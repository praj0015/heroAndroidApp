package com.example.hero_api_project

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelStore
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hero_api_project.database.HeroDao
import com.example.hero_api_project.database.HeroDatabase
import com.example.hero_api_project.databinding.ActivityMainBinding
import com.example.hero_api_project.factory.ViewModelFactory
import com.example.hero_api_project.model.HeroModel
import com.example.hero_api_project.recycleViewAdapter.RecycleViewAdapter
import com.example.hero_api_project.repository.HeroRepository
import com.example.hero_api_project.retrofit.RestAPI
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity() : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding : ActivityMainBinding
    private lateinit var viewModel: AppViewModel

    companion object {
        val INTENT_PARCELABLE = "OBJECT_INTENT"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        recyclerView = findViewById(R.id.recyclerView)

        val heroRepositoryDao = HeroDatabase.getDatabase(this).heroDao()
        val heroRepository = HeroRepository(heroRepositoryDao)
        val newViewModel : AppViewModel by viewModels {
            ViewModelFactory(heroRepository)
        }
        viewModel = newViewModel
        binding.heroViewModel = viewModel
        binding.lifecycleOwner = this

        if(isNetworkConnected(this)) {
            viewModel.getHeroesFromAPIAndStore()
            Toast.makeText(this, "Data successfully stored in database", Toast.LENGTH_SHORT)
        } else {
            Toast.makeText(this,"No internet found. Showing cached list in the view", Toast.LENGTH_LONG).show()
        }

        viewModel.getAllHeroesList().observe(this, Observer<List<HeroModel>> { heroList ->
            Log.d(MainActivity::class.java.simpleName,heroList.toString())
            showData(heroList)
        })
//        var rf = Retrofit.Builder()
//            .baseUrl(RestAPI.BASE_URL)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        var API = rf.create(RestAPI::class.java)
//        var call = API.getAllHeroes()
//
//        call?.enqueue(object : Callback<List<HeroModel>> {
//            override fun onResponse(
//                call: Call<List<HeroModel>>,
//                response: Response<List<HeroModel>>
//            ) {
//                showData(response.body()!!)
//            }
//
//            override fun onFailure(call: Call<List<HeroModel>>, t: Throwable) {
//                Log.d("", "There is an error in fetch")
//            }
//
//        })
    }

    fun isNetworkConnected(context: Context): Boolean {
        val cm = context.getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork = cm.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting
    }

    private fun showData(heroes: List<HeroModel>) {
        val recyclerViewAdapter = RecycleViewAdapter(this,heroes)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter = recyclerViewAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}