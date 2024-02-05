package com.example.jakdangmodok

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.jakdangmodok.databinding.ActivityGroupDetailsBinding
import com.naver.maps.map.NaverMapSdk
import kotlinx.coroutines.launch

class GroupDetailsActivity : AppCompatActivity() {

    private val binding by lazy { ActivityGroupDetailsBinding.inflate(layoutInflater) }
    private val bookAPIService = BookAPIService()

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

        setBookInfo(intent.getStringExtra("isbn")!!)
        initNaverMap()
        initView()
    }

    private fun initView() {
        binding.groupTitle.text = intent.getStringExtra("groupName")
        binding.dateDetail.text = intent.getStringExtra("date")
        //binding.placeDetail.text = intent.getStringExtra("place")
        binding.introductionDetail.text = intent.getStringExtra("introduction")
        binding.feeDetail.text = intent.getStringExtra("fee")

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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }
    @SuppressLint("ClickableViewAccessibility")
    private fun initNaverMap() {
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

    private fun setBookInfo(isbn: String) {
        lifecycleScope.launch {
            val book = bookAPIService.getBookDetail(isbn)
            binding.bookTitle.text = book.title
            binding.bookAuthor.text = book.author
            binding.bookPage.text = book.itemPage
            binding.bookGenre.text = book.categoryName
            Glide.with(binding.root.context)
                .load(book.cover)
                .into(binding.bookImage)
        }
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