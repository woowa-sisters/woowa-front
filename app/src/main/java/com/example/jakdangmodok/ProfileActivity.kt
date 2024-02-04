package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jakdangmodok.databinding.ActivityProfileBinding

// 데이터 클래스 생성하기. (text: 장르명 문자열)
data class FavoriteGenreItem(val text: String)

class ProfileActivity : AppCompatActivity() {
    private val binding by lazy { ActivityProfileBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        buttonClick()

        // 관심장르 리사이클러뷰
        val rv_fgenre = findViewById<RecyclerView>(R.id.recyclerview_favorite_genre)

        //todo : 관심 장르 데이터 받아오기
        val itemList = ArrayList<FavoriteGenreItem>()
        itemList.add(FavoriteGenreItem("SF"))
        itemList.add(FavoriteGenreItem("추리"))
        itemList.add(FavoriteGenreItem("로맨스"))

        val genreFavoriteAdapter = GenreFavoriteAdapter(itemList)
        genreFavoriteAdapter.notifyDataSetChanged()

        rv_fgenre.adapter = genreFavoriteAdapter
        rv_fgenre.layoutManager = GridLayoutManager(this, 3) // 관심 장르는 최대 3개만 고를 수 있도록
        // 장르 1,2,3개 선택 시 모두 각각의 장르버튼 크기는 같음

        val spacingInPixels = resources.getDimensionPixelSize(R.dimen.favorite_genre_dimen)
        rv_fgenre.addItemDecoration(GridSpaceItemDecoration(3, spacingInPixels))

    }

    private fun buttonClick() {
        binding.buttonProfileEdit.setOnClickListener {
            val intent = Intent(this, ProfileEditActivity::class.java)
            startActivity(intent)
        }
        binding.buttonSettingNotification.setOnClickListener {
            val intent = Intent(this, ProfileNotificationActivity::class.java)
            startActivity(intent)
        }

        binding.buttonApplyGroup.setOnClickListener {
            val intent = Intent(this, ApplyGroupActivity::class.java)
            startActivity(intent)
        }

        binding.buttonMyGroup.setOnClickListener {
            val intent = Intent(this, MyGroupActivity::class.java)
            startActivity(intent)
        }

        binding.buttonCompleteList.setOnClickListener {
            val intent = Intent(this, CompleteGroupActivity::class.java)
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