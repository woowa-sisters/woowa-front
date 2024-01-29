package com.example.jakdangmodok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.ActivityCompleteGroupBinding

class CompleteGroupActivity : AppCompatActivity() {

    val binding by lazy { ActivityCompleteGroupBinding.inflate(layoutInflater) }

    private val groupList: ArrayList<String> = arrayListOf("코딩모임", "영화모임", "독서모임", "게임모임", "운동모임", "요리모임", "여행모임", "공연모임", "음악모임", "봉사모임", "기타모임")


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarGroupDetails)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // 완료 모임 목록
        binding.recyclerviewCompleteList.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewCompleteList.adapter = CompleteGroupAdapter(groupList)

        }

    }