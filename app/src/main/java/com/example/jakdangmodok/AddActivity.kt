package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.TextView
import com.example.jakdangmodok.databinding.ActivityAddBinding

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