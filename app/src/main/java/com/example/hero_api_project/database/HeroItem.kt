package com.example.hero_api_project.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Heroes")
data class HeroItem(
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0,

    @ColumnInfo(name = "name")
    var name : String? = null,

    @ColumnInfo(name = "localized_name")
    var localized_name : String? = null,

    @ColumnInfo(name = "attack_type")
    var attack_type : String? = null,

    @ColumnInfo(name = "base_health")
    var base_health : String? = null,

    @ColumnInfo(name = "primary_attr")
    var primary_attr : String? = null
)
