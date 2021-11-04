package com.example.hero_api_project

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.hero_api_project.database.HeroItem
import com.example.hero_api_project.model.HeroModel
import com.example.hero_api_project.repository.HeroRepository

class AppViewModel(private val repository: HeroRepository) : ViewModel() {

    fun getAllHeroesList() : LiveData<List<HeroModel>> {
        return repository.getAllHeroes()
    }
    fun getHeroesFromAPIAndStore() {
        return repository.ApiCallAndStoreInDB()
    }
}