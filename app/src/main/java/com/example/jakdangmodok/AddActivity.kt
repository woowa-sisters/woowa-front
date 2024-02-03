package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.DatePicker
import android.widget.EditText
import android.widget.SearchView
import android.widget.TextView
import com.example.jakdangmodok.databinding.ActivityAddBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class AddActivity : AppCompatActivity() {

    private val binding by lazy { ActivityAddBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbarAdd)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        initButton()

        // 툴바 이름 변경
        val toolbarTitle = findViewById<TextView>(R.id.toolbar_add_title)
        toolbarTitle.text = "모임 수정하기"

        // 버튼 텍스트 변경
        val buttonAdd = findViewById<Button>(R.id.button_add)
        buttonAdd.text = "모임 수정하기"

        // '모임 수정하기' 버튼 클릭 이벤트 처리
        buttonAdd.setOnClickListener {
            // 각각의 뷰에 대한 변수 설정하기
            val groupTitle = findViewById<EditText>(R.id.edittext_group_name)
            // TODO : 책 정보 받아올 코드
            val datepicker = findViewById<DatePicker>(R.id.datepicker_add)
            val year = datepicker.year
            val month = datepicker.month
            val day = datepicker.dayOfMonth
            // TODO : 서치뷰에서 정한 장소 데이터 받아올 코드
            val memberCount = findViewById<TextView>(R.id.group_member_count)
            val groupInfo = findViewById<EditText>(R.id.edittext_group_intro)
            val fee = findViewById<EditText>(R.id.edt_fee)

            // 뷰에 입력받은 내용 형변환하여 가져오기
            val updatedGroupTitle = groupTitle.text.toString()
            // TODO : 책 정보 입력받은 내용 형변환하여 가져오기
            val calendar = Calendar.getInstance() // Calendar 객체를 사용하여 날짜 정보 설정
            calendar.set(year, month, day)
            // SimpleDateFormat을 사용하여 원하는 형식의 날짜 문자열로 변환
            val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val dateString: String = simpleDateFormat.format(calendar.time) // dateString이 최종 변수임
            // TODO : 서치뷰에서 정한 장소데이터 형변환하여 가져오기
            val updatedMember = memberCount.text.toString()
            val updatedGroupInfo = groupInfo.text.toString()
            val updatedFee = fee.text.toString()

            // Intent 생성하기 (GroupDetailsActivity로 데이터 보낼 인텐트 준비)
            val intent = Intent(this, GroupDetailsActivity::class.java)
            intent.putExtra("updatedGroupTitle", updatedGroupTitle)
            intent.putExtra("dateString", dateString)
            intent.putExtra("updatedMember", updatedMember)
            intent.putExtra("updatedGroupInfo", updatedGroupInfo)
            intent.putExtra("updatedFee", updatedFee)
            // TODO : 책 정보, 장소 정보도 보내야 한다.

            // GroupDetailsActivity로 전환
            startActivity(intent)
        }
    }

    private fun initButton() {
        binding.searchBookAdd.setOnClickListener {
            // 책 검색 액티비티로 이동
            val intent = Intent(this, BookSearchActivity::class.java)
            startActivity(intent)
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