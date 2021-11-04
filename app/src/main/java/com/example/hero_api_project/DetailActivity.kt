package com.example.hero_api_project

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        var heroData = intent
        var heroName = heroData.getStringExtra("heroName")
        var hero_localized_name = heroData.getStringExtra("hero_localized_name")
        var hero_primary_attr = heroData.getStringExtra("hero_primary_attr")

        var resource_hero_name = findViewById<TextView>(R.id.name)
        var resource_localized_name = findViewById<TextView>(R.id.localized_name)
        var resource_primary_attr = findViewById<TextView>(R.id.primary_attr)

        resource_hero_name.text = heroName
        resource_localized_name.text = hero_localized_name
        resource_primary_attr.text = hero_primary_attr

    }
}