package com.example.jakdangmodok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.ActivityPlaceSearchBinding
import kotlinx.coroutines.launch

class PlaceSearchActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPlaceSearchBinding.inflate(layoutInflater) }
    private val naverMapAPIService = NaverMapAPIService()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.recyclerviewSearchPlace.layoutManager = LinearLayoutManager(this)
        setSearchView()

        setContentView(binding.root)
    }

    private fun setSearchView() {
        binding.searchViewPlace.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                lifecycleScope.launch {
                    binding.recyclerviewSearchPlace.adapter = PlaceSearchAdapter(naverMapAPIService.getMapSearch(query!!))
                    binding.recyclerviewSearchPlace.isVisible = true
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }

}