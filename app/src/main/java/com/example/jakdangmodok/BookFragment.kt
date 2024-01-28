package com.example.jakdangmodok

import android.R
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.FragmentBookBinding
import kotlinx.coroutines.launch

class BookFragment : Fragment() {

    private val apiBookService = apiBookService()
    private val filterList: Array<String> = arrayOf("최신순", "마감순", "거리순")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookBinding.inflate(inflater, container, false)

        initSearchView(binding)

        val filterAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, filterList)
        filterAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        lifecycleScope.launch {
            binding.recyclerviewBook.layoutManager = LinearLayoutManager(activity)
            binding.recyclerviewBook.adapter = BookAdapter(apiBookService.getBookList())
            binding.spinnerBook.adapter = filterAdapter
        }

        return binding.root
    }

    private fun initSearchView(binding: FragmentBookBinding) {
        binding.searchBook.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                binding.recyclerviewBook.adapter = BookAdapter(apiBookService.getBookSearch(query!!))
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

}