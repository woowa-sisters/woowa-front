package com.example.jakdangmodok

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.jakdangmodok.databinding.ItemGenreBinding

class GenreViewHolder(val binding: ItemGenreBinding): RecyclerView.ViewHolder(binding.root) {
    init {
        binding.genreButton.setOnClickListener() {
            val context = binding.root.context

            // 버튼 선택
            if (binding.genreButton.textColors.defaultColor == ContextCompat.getColor(context, R.color.gray)) {
                binding.genreButton.setBackgroundResource(R.drawable.genre_selected)
                binding.genreButton.setTextColor(Color.WHITE)
            } else { // 버튼 선택 해제
                binding.genreButton.setBackgroundResource(R.drawable.genre_unselected)
                binding.genreButton.setTextColor(ContextCompat.getColor(context, R.color.gray))
            }
        }
    }
}

class GenreAdapter: RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    val genreList = listOf("소설", "시/에세이", "경제/경영", "자기계발", "인문", "역사/문화", "사회", "과학", "예술/대중문화", "종교", "외국어", "기술/공학", "컴퓨터/IT", "취미/스포츠", "건강/다이어트", "가정/육아", "요리", "여행", "교재/수험서", "커리어/수험서", "청소년", "어린이", "만화", "잡지", "해외도서", "오디오북")

    override fun getItemCount(): Int = genreList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):
            RecyclerView.ViewHolder
            = GenreViewHolder(ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val binding = (holder as GenreViewHolder).binding

        binding.genreButton.text = genreList[position]
    }

}