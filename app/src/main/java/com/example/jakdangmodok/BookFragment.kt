package com.example.jakdangmodok

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.FragmentBookBinding

class BookFragment : Fragment() {

    private val filterList: Array<String> = arrayOf("최신순", "마감순", "거리순")
    private val bookList: ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookBinding.inflate(inflater, container, false)

        val filterAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, filterList)
        filterAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.recyclerviewBook.layoutManager = LinearLayoutManager(activity)
        binding.recyclerviewBook.adapter = BookAdapter(bookList)
        binding.spinnerBook.adapter = filterAdapter

        return binding.root
    }

}