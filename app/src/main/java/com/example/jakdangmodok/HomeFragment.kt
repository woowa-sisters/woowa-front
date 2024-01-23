package com.example.jakdangmodok

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

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
// 어댑터 생성하고 바인딩하기
        return binding.root
    }
}
