package com.example.jakdangmodok

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class GenreFavoriteAdapter(val itemList: ArrayList<FavoriteGenreItem>) :
    RecyclerView.Adapter<GenreFavoriteAdapter.GenreFavoriteViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreFavoriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_genre_favorite, parent, false)
        return GenreFavoriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: GenreFavoriteViewHolder, position: Int) {
        holder.btn_fgenre.text = itemList[position].text
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    class GenreFavoriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val btn_fgenre = itemView.findViewById<Button>(R.id.genre_favorite_button)
    }
    
}
