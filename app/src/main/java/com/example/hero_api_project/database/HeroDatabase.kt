package com.example.hero_api_project.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.hero_api_project.model.HeroModel

@Database(entities = arrayOf(HeroModel::class), version = 1, exportSchema = false)
abstract class HeroDatabase : RoomDatabase() {

    abstract fun heroDao() : HeroDao

    companion object {
        private var INSTANCE : HeroDatabase? = null

        fun getDatabase(context: Context) : HeroDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    HeroDatabase::class.java,
                    "Heroes"
                ).build()

                INSTANCE = instance
                return instance
            }
        }
    }
}