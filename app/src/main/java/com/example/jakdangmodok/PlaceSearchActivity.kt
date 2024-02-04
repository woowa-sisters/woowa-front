package com.example.jakdangmodok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import com.example.jakdangmodok.databinding.ActivityPlaceSearchBinding

class PlaceSearchActivity : AppCompatActivity() {

    private val binding by lazy { ActivityPlaceSearchBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSearchView()

        setContentView(binding.root)
    }

    private fun setSearchView() {
        binding.searchViewPlace.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}