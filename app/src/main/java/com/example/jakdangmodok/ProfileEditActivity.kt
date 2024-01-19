package com.example.jakdangmodok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.ActivityProfileEditBinding

class ProfileEditActivity : AppCompatActivity() {
    private val binding by lazy { ActivityProfileEditBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerviewGenre.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerviewGenre.adapter = GenreAdapter()
        binding.recyclerviewGenre.addItemDecoration(GridSpaceItemDecoration(3, 20))
    }
}