package com.example.jakdangmodok

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jakdangmodok.databinding.ItemSearchPlaceResultBinding

class PlaceSearchViewHolder(val binding: ItemSearchPlaceResultBinding): RecyclerView.ViewHolder(binding.root)

class PlaceSearchAdapter(val placeList: List<Place>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemCount(): Int = placeList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder
            = PlaceSearchViewHolder(ItemSearchPlaceResultBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as PlaceSearchViewHolder).binding

        binding.placeName.text = placeList[position].title
        binding.placeAddress.text = placeList[position].address
    }

}