package com.example.jakdangmodok

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.jakdangmodok.databinding.FragmentHomeBinding
import kotlinx.coroutines.launch


class HomeFragment : Fragment() {

    private val bookAPIService = BookAPIService()

    private val groupList: ArrayList<String> = arrayListOf("코딩모임", "영화모임", "독서모임", "게임모임", "운동모임", "요리모임", "여행모임", "공연모임", "음악모임", "봉사모임", "기타모임")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentHomeBinding.inflate(inflater, container, false)

        initBook(binding)
        initGroup(binding)
        setClickListeners(binding)

        return binding.root
    }

    private fun initBook(binding: FragmentHomeBinding) {
        lifecycleScope.launch {
            val bookList = bookAPIService.getBookList()
            var index = 0
            val maxIndex = bookList.size - 1
            val defaultImage = ContextCompat.getDrawable(binding.root.context, R.drawable.ic_launcher_background)

            // 메인 추천 도서
            binding.bookTitle.text = bookList[index].title
            binding.bookAuthor.text = bookList[index].author
            Glide.with(binding.root.context)
                .load(bookList[index].cover)
                .error(defaultImage)
                .fallback(defaultImage)
                .into(binding.bookCoverCenter)
            binding.bookIsbnHome.text = bookList[index].isbn

            // 좌우 추천 도서
            Glide.with(binding.root.context)
                .load(bookList[maxIndex].cover)
                .error(defaultImage)
                .fallback(defaultImage)
                .into(binding.bookCoverLeft)

            Glide.with(binding.root.context)
                .load(bookList[1].cover)
                .error(defaultImage)
                .fallback(defaultImage)
                .into(binding.bookCoverRight)

            // 왼쪽 버튼
            binding.buttonLeft.setOnClickListener {
                if (index > 0) {
                    binding.bookTitle.text = bookList[index-1].title
                    binding.bookAuthor.text = bookList[index-1].author
                    Glide.with(binding.root.context)
                        .load(bookList[index-1].cover)
                        .error(defaultImage)
                        .fallback(defaultImage)
                        .into(binding.bookCoverCenter)
                    binding.bookIsbnHome.text = bookList[index-1].isbn
                    if (index == 1) {
                        Glide.with(binding.root.context)
                            .load(bookList[maxIndex].cover)
                            .error(defaultImage)
                            .fallback(defaultImage)
                            .into(binding.bookCoverLeft)
                    } else {
                        Glide.with(binding.root.context)
                            .load(bookList[index-2].cover)
                            .error(defaultImage)
                            .fallback(defaultImage)
                            .into(binding.bookCoverLeft)
                    }
                    Glide.with(binding.root.context)
                        .load(bookList[index].cover)
                        .error(defaultImage)
                        .fallback(defaultImage)
                        .into(binding.bookCoverRight)

                    index--
                } else {
                    binding.bookTitle.text = bookList[maxIndex].title
                    binding.bookAuthor.text = bookList[maxIndex].author
                    Glide.with(binding.root.context)
                        .load(bookList[maxIndex].cover)
                        .error(defaultImage)
                        .fallback(defaultImage)
                        .into(binding.bookCoverCenter)
                    binding.bookIsbnHome.text = bookList[maxIndex].isbn

                    Glide.with(binding.root.context)
                        .load(bookList[maxIndex-1].cover)
                        .error(defaultImage)
                        .fallback(defaultImage)
                        .into(binding.bookCoverLeft)
                    Glide.with(binding.root.context)
                        .load(bookList[index].cover)
                        .error(defaultImage)
                        .fallback(defaultImage)
                        .into(binding.bookCoverRight)

                    index = maxIndex
                }
            }

            // 오른쪽 버튼
            binding.buttonRight.setOnClickListener {
                if (index < maxIndex) {
                    binding.bookTitle.text = bookList[index+1].title
                    binding.bookAuthor.text = bookList[index+1].author
                    Glide.with(binding.root.context)
                        .load(bookList[index+1].cover)
                        .error(defaultImage)
                        .fallback(defaultImage)
                        .into(binding.bookCoverCenter)
                    binding.bookIsbnHome.text = bookList[index+1].isbn

                    Glide.with(binding.root.context)
                        .load(bookList[index].cover)
                        .error(defaultImage)
                        .fallback(defaultImage)
                        .into(binding.bookCoverLeft)
                    if (index == maxIndex-1) {
                        Glide.with(binding.root.context)
                            .load(bookList[0].cover)
                            .error(defaultImage)
                            .fallback(defaultImage)
                            .into(binding.bookCoverRight)
                    } else {
                        Glide.with(binding.root.context)
                            .load(bookList[index+2].cover)
                            .error(defaultImage)
                            .fallback(defaultImage)
                            .into(binding.bookCoverRight)
                    }

                    index++
                } else {
                    binding.bookTitle.text = bookList[0].title
                    binding.bookAuthor.text = bookList[0].author
                    Glide.with(binding.root.context)
                        .load(bookList[0].cover)
                        .error(defaultImage)
                        .fallback(defaultImage)
                        .into(binding.bookCoverCenter)
                    binding.bookIsbnHome.text = bookList[0].isbn

                    Glide.with(binding.root.context)
                        .load(bookList[maxIndex].cover)
                        .error(defaultImage)
                        .fallback(defaultImage)
                        .into(binding.bookCoverLeft)
                    Glide.with(binding.root.context)
                        .load(bookList[1].cover)
                        .error(defaultImage)
                        .fallback(defaultImage)
                        .into(binding.bookCoverRight)

                    index = 0
                }
            }
        }
    }

    private fun initGroup(binding: FragmentHomeBinding) {

        // 검색창 이동 버튼
        binding.buttonSearch.setOnClickListener {
            val intent = Intent(binding.root.context, BookSearchActivity::class.java)
            startActivity(intent)
        }

        // 최근 생성된 모임
        binding.recyclerviewGroupNew.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewGroupNew.adapter = GroupAdapter(groupList)

        // 마감 임박 모임
        binding.recyclerviewGroupClosingsoon.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerviewGroupClosingsoon.adapter = GroupAdapter(groupList)
    }

    private fun setClickListeners(binding: FragmentHomeBinding) {
        binding.bookCoverCenter.setOnClickListener {
            val intent = Intent(binding.root.context, BookDetailActivity::class.java)
            intent.putExtra("isbn", binding.bookIsbnHome.text.toString())
            binding.root.context.startActivity(intent)
        }
    }
}
