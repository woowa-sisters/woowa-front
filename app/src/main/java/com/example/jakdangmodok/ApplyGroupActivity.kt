package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.ActivityApplyGroupBinding

class ApplyGroupActivity : AppCompatActivity() {

    val binding by lazy { ActivityApplyGroupBinding.inflate(layoutInflater) }
    private val groupList: ArrayList<String> = arrayListOf("코딩모임", "영화모임", "독서모임", "게임모임", "운동모임", "요리모임", "여행모임", "공연모임", "음악모임", "봉사모임", "기타모임")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarApplyGroup)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        // 신청 모임 목록
        binding.recyclerviewApplyGroup.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewApplyGroup.adapter = ApplyGroupAdapter(groupList)

    }

    // 뒤로가기 버튼
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

}