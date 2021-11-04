package com.example.hero_api_project.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.hero_api_project.model.HeroModel

@Dao
interface HeroDao {

    @Query("SELECT * FROM Heroes")
    fun getAllHeroes() : LiveData<List<HeroModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(Hero : List<HeroModel>)

    @Query("DELETE FROM Heroes")
    fun deleteAll()
}