package com.example.jakdangmodok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.ActivityNotificationBinding

class NotificationActivity : AppCompatActivity() {
    private val binding by lazy { ActivityNotificationBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerviewNotification.layoutManager = layoutManager
        // todo : binding.recyclerviewNotification.adapter = NotificationAdapter()
        // 수평 구분선 추가
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerviewNotification.context,
            LinearLayoutManager.VERTICAL
        )
        binding.recyclerviewNotification.addItemDecoration(dividerItemDecoration)
    }
}