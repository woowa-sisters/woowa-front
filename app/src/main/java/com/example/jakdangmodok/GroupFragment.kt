package com.example.jakdangmodok

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.FragmentGroupBinding

class GroupFragment : Fragment() {

    private val filterList: Array<String> = arrayOf("최신순", "마감순", "거리순")
    private val groupList: ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGroupBinding.inflate(inflater, container, false)

        val filterAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, filterList)
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.recyclerviewGroup.layoutManager = LinearLayoutManager(activity)
        binding.recyclerviewGroup.adapter = GroupAdapter(groupList)
        binding.spinnerGroup.adapter = filterAdapter

        return binding.root
    }

}