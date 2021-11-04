package com.example.hero_api_project.recycleViewAdapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.example.hero_api_project.DetailActivity
import com.example.hero_api_project.R
import com.example.hero_api_project.model.HeroModel
import com.example.hero_api_project.retrofit.RestAPI
import com.squareup.picasso.Picasso

class RecycleViewAdapter( private val context: Context,
                          private val heroes : List<HeroModel>) :
    RecyclerView.Adapter<RecycleViewAdapter.HeroViewHolder>()
{
    class HeroViewHolder (itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HeroViewHolder {
        return HeroViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.hero_items,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HeroViewHolder, position: Int) {
        val curHeroes = heroes[position]
        holder.itemView.apply {
            val heroName = findViewById<TextView>(R.id.hero_name)
            val image = findViewById<ImageView>(R.id.imageView)
            curHeroes.img = RestAPI.BASE_URL + curHeroes.img
            Picasso.get().load(curHeroes.img).into(image)
            heroName.text = curHeroes.localized_name
            holder.itemView.setOnClickListener {
                var intent = Intent(context, DetailActivity::class.java)
                intent.putExtra("heroName", curHeroes.name)
                intent.putExtra("hero_localized_name", curHeroes.localized_name)
                intent.putExtra("hero_primary_attr", curHeroes.primary_attr)
                context.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return heroes.size
    }
}
