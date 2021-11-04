package com.example.hero_api_project.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.hero_api_project.AppViewModel
import com.example.hero_api_project.repository.HeroRepository

class ViewModelFactory(private val heroRepository: HeroRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(AppViewModel::class.java)) {
            return AppViewModel(heroRepository) as T
        }
        throw IllegalArgumentException("Unknown View Model class")
    }
}