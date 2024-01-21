package com.example.jakdangmodok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.ActivityGroupDetailsBinding

class GroupDetailsActivity : AppCompatActivity() {

    val binding by lazy { ActivityGroupDetailsBinding.inflate(layoutInflater) }
    private val memberList: ArrayList<String> = arrayListOf("김단비", "연두", "우주")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.recyclerviewMember.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewMember.adapter = MemberAdapter(memberList)
    }

}