package com.example.jakdangmodok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.jakdangmodok.databinding.ActivitySignupGenreselectBinding

class SignupGenreselectActivity : AppCompatActivity() {
    private val binding by lazy { ActivitySignupGenreselectBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerviewGenre.layoutManager = GridLayoutManager(this, 3)
        binding.recyclerviewGenre.adapter = GenreAdapter()
        binding.recyclerviewGenre.addItemDecoration(GridSpaceItemDecoration(3, 20))
    }
}