package com.example.jakdangmodok

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
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

        // 모임 수정 페이지에서 수정한 정보 가져오기
        val intent = intent
        val updatedGroupTitle: String? = intent.getStringExtra("updatedGroupTitle")
        val dateString: String? = intent.getStringExtra("dateString")
        val updatedMember: String? = intent.getStringExtra("updatedMember")
        val updatedGroupInfo: String? = intent.getStringExtra("updatedGroupInfo")
        val updatedFee: String? = intent.getStringExtra("updatedFee")

        // 받아온 데이터를 텍스트뷰로 보여주기
        val groupTitle = findViewById<TextView>(R.id.group_title)
        groupTitle.text = updatedGroupTitle
        val date = findViewById<TextView>(R.id.textview_date)
        date.text = dateString
        val member = findViewById<TextView>(R.id.member_now) // todo: 멤버수 "m / n" 포맷대로 보여주기
        member.text = updatedMember
        val groupInfo = findViewById<TextView>(R.id.introduction)
        groupInfo.text = updatedGroupInfo
        val fee = findViewById<TextView>(R.id.fee)
        fee.text = updatedFee

        //TODO : 책 정보, 지도 정보 받아온 데이터를 보여주기

        //TODO : 받아온 데이터를 데이터베이스에 저장..(?)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun initNaverMap() {
        NaverMapSdk.getInstance(this).client =
            NaverMapSdk.NaverCloudPlatformClient(ID)

        binding.mapView.setOnTouchListener { view, motionEvent ->
            when (motionEvent.action) {
                MotionEvent.ACTION_DOWN -> {
                    binding.scrollviewGroupDetails.requestDisallowInterceptTouchEvent(true)
                    false
                }
                MotionEvent.ACTION_UP -> {
                    binding.scrollviewGroupDetails.requestDisallowInterceptTouchEvent(false)
                    true
                }
                MotionEvent.ACTION_MOVE -> {
                    binding.scrollviewGroupDetails.requestDisallowInterceptTouchEvent(true)
                    false
                }
                else -> true
            }
        }
    }

            override fun onOptionsItemSelected(item: MenuItem): Boolean {
                return when (item.itemId) {
                    // 뒤로가기 버튼
                    android.R.id.home -> {
                        finish()
                        true
                    }
                    // 모임 수정 버튼 클릭 시 AddActivity로 전환
                    R.id.menu_correction -> {
                        val intent = Intent(this, AddActivity::class.java)
                        startActivity(intent)
                        return true
                    }

                    else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
        private const val ID = "sjrtcbmv0g"
    }

}