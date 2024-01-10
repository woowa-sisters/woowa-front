package com.example.jakdangmodok

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.FragmentBookBinding

class BookFragment : Fragment() {

    val bookList: ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookBinding.inflate(inflater, container, false)

        binding.recyclerviewBook.layoutManager = LinearLayoutManager(activity)
        binding.recyclerviewBook.adapter = BookAdapter(bookList)

        return binding.root
    }

}