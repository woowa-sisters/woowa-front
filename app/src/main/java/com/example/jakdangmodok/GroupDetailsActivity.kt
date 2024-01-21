package com.example.jakdangmodok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.ActivityGroupDetailsBinding

class GroupDetailsActivity : AppCompatActivity() {

    val binding by lazy { ActivityGroupDetailsBinding.inflate(layoutInflater) }
    private val memberList: ArrayList<String> = arrayListOf("김단비", "연두", "우주", "희", "유나", "유진", "유정")
    private val memberWaitingList: ArrayList<String> = arrayListOf("에이든", "피터", "토니", "스티브", "브루스", "토르", "클린트", "나타샤", "와칸다", "페퍼", "헬라")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // 멤버 소개
        binding.recyclerviewMember.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewMember.adapter = MemberAdapter(memberList)

        // 가입 신청자
        binding.recyclerviewMemberWaiting.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewMemberWaiting.adapter = MemberWaitingAdapter(memberList)
    }

}