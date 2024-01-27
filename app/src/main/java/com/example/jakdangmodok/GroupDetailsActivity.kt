package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.ActivityGroupDetailsBinding
import com.naver.maps.map.NaverMapSdk

class GroupDetailsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityGroupDetailsBinding.inflate(layoutInflater) }

    private val memberList: ArrayList<String> = arrayListOf("김단비", "연두", "우주", "희", "유나", "유진", "유정")
    private val memberWaitingList: ArrayList<String> = arrayListOf("에이든", "피터", "토니", "스티브", "브루스", "토르", "클린트", "나타샤", "와칸다", "페퍼", "헬라")
    private val groupList: ArrayList<String> = arrayListOf("코딩모임", "영화모임", "독서모임", "게임모임", "운동모임", "요리모임", "여행모임", "공연모임", "음악모임", "봉사모임", "기타모임")
    private val commentList: ArrayList<String> = arrayListOf("안녕하세요", "반갑습니다", "모임장님 안녕하세요", "모임장님 반갑습니다")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarGroupDetails)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initNaverMap()

        // 모임명
        intent.getStringExtra("groupId")?.let {
            binding.groupTitle.text = it
        }

        // 멤버 소개
        binding.recyclerviewMember.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewMember.adapter = MemberAdapter(memberList)

        // 가입 신청자
        binding.recyclerviewMemberWaiting.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewMemberWaiting.adapter = MemberWaitingAdapter(memberWaitingList)

        // 모임장의 다른 모임
        binding.recyclerviewOtherGroup.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewOtherGroup.adapter = GroupAdapter(groupList)

        // 댓글
        binding.recyclerviewComment.layoutManager = LinearLayoutManager(this)
        binding.recyclerviewComment.adapter = CommentAdapter(commentList)
    }

    private fun initNaverMap() {
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient(ID)
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

    companion object {
        private const val ID = "sjrtcbmv0g"
    }

}