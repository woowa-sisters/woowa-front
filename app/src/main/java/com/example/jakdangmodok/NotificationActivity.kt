package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jakdangmodok.databinding.ActivityNotificationBinding

// 데이터 클래스 생성하기. (text: 알림 메시지 문자열, time: 작성 시간 문자열)
data class NotificationItem(val text: String, val date: String)

class NotificationActivity : AppCompatActivity() {
    private val binding by lazy { ActivityNotificationBinding.inflate(layoutInflater) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        val rv_notice = findViewById<RecyclerView>(R.id.recyclerview_notification)

        val itemList = ArrayList<NotificationItem>()
        itemList.add(NotificationItem("xx모임의 모임 승인 요청이 완료되었습니다.", "02/02 1:31")) // 모임 승인 요청[그룹원]
        itemList.add(NotificationItem("00님이 xx모임에 모임 승인을 요청하였습니다.", "02/02 1:31")) // 모임 승인 요청[모임장]
        itemList.add(NotificationItem("xx모임이 관심 모임으로 등록되었습니다.", "02/02 1:31")) // 관심 모임[그룹원]
        itemList.add(NotificationItem("00님이 xx모임을 관심 모임으로 등록하였습니다.", "02/02 1:31")) // 관심 모임[모임장]
        itemList.add(NotificationItem("00님이 xx모임에 댓글을 작성하였습니다.", "02/02 1:31")) // 댓글 알림[아마 모임장한테만 알림 가도록]


        val notificationAdapter = NotificationAdapter(itemList)
        notificationAdapter.notifyDataSetChanged()

        rv_notice.adapter = notificationAdapter
        rv_notice.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        //binding.recyclerviewNotification.adapter = NotificationAdapter()

        // 수평 구분선 추가
        val dividerItemDecoration = DividerItemDecoration(
            binding.recyclerviewNotification.context,
            LinearLayoutManager.VERTICAL
        )
        binding.recyclerviewNotification.addItemDecoration(dividerItemDecoration)
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