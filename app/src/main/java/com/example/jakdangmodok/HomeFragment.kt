package com.example.jakdangmodok

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private val apiBookService = apiBookService()

    private val mainbookTitleList: ArrayList<String> = arrayListOf("내 인생의 책들", "미래는 저녁8시", "감정의 문화정치")
    private val mainbookAuthorList: ArrayList<String> = arrayListOf("리처드 도킨스", "가운데 사람", "오른쪽 사람")
    private val groupList: ArrayList<String> = arrayListOf("코딩모임", "영화모임", "독서모임", "게임모임", "운동모임", "요리모임", "여행모임", "공연모임", "음악모임", "봉사모임", "기타모임")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)



        init(binding)

        return binding.root
    }

    private fun init(binding: FragmentHomeBinding) {
        // 메인 책장
        lifecycleScope.launch {
            binding.recyclerviewMainbook.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
            binding.recyclerviewMainbook.adapter = HomeBookAdapter(apiBookService.getBookList())
        }

        // 검색창 이동 버튼
        binding.buttonSearch.setOnClickListener {
            val intent = Intent(binding.root.context, BookSearchActivity::class.java)
            startActivity(intent)
        }

        // 최근 생성된 모임
        binding.recyclerviewGroupNew.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewGroupNew.adapter = GroupAdapter(groupList)

        // 마감 임박 모임
        binding.recyclerviewGroupClosingsoon.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewGroupClosingsoon.adapter = GroupAdapter(groupList)
    }

}
