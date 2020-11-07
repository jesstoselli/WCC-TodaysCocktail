package com.androidwcc.todayscocktail.view

import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.androidwcc.todayscocktail.R
import com.androidwcc.todayscocktail.network.Cocktail

class CocktailItemsAdapter: RecyclerView.Adapter<CocktailItemsAdapter.CocktailItemViewHolder>() {
    var data = listOf<Cocktail>()
        set(value){
            field = value
            notifyDataSetChanged()
        }

    inner class CocktailItemViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val cocktailName: TextView = itemView.findViewById(R.id.tv_cocktailName)
        val cocktailImage: ImageView = itemView.findViewById(R.id.iv_cocktailImage)

        fun bind(name: String, image: ImageView){
            cocktailName.text = name
            cocktailImage.setImageURI() = image
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CocktailItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemView = layoutInflater.inflate(R.layout.cocktail_item, parent, false)
        return CocktailItemViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: CocktailItemViewHolder, position: Int) {
        val cocktailItemToShow: Cocktail = data.get(position)
        holder.bind(cocktailItemToShow.name, cocktailItemToShow.thumbUrl)

    }
}