package com.example.jakdangmodok

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.FragmentSelectGenreBinding

class SelectGenreFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectGenreBinding.inflate(inflater, container, false)

        addGenre(binding)

        return binding.root
    }

    private fun addGenre(binding: FragmentSelectGenreBinding) {
        binding.recyclerviewGenreSignUp.layoutManager = GridLayoutManager(context, 3)
        binding.recyclerviewGenreSignUp.adapter = GenreAdapter()
    }

}