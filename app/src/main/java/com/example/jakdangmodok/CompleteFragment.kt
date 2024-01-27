package com.example.jakdangmodok

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.FragmentCompleteBinding

class CompleteFragment : Fragment() {

    private val completeList: ArrayList<String> = arrayListOf("코딩모임", "영화모임", "독서모임", "게임모임", "운동모임", "요리모임", "여행모임", "공연모임", "음악모임", "봉사모임", "기타모임")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCompleteBinding.inflate(inflater, container, false)

        binding.recyclerviewComplete.layoutManager = LinearLayoutManager(activity)
        binding.recyclerviewComplete.adapter = CompleteAdapter(completeList)

        return binding.root
    }

}