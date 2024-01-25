package com.example.jakdangmodok

import android.R
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jakdangmodok.databinding.FragmentBookBinding
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class BookFragment : Fragment() {

    private val gson = GsonBuilder().setLenient().create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://www.aladin.co.kr/ttb/api/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    private val ttbkey: String = "ttbnaahyin02016001"
    private val query: String = "ItemNewSpecial"

    private val bookService = retrofit.create(BookService::class.java)

    private val filterList: Array<String> = arrayOf("최신순", "마감순", "거리순")
    private val bookList: ArrayList<String> = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBookBinding.inflate(inflater, container, false)

        getBookList(query)
        getBookSearch("안드로이드")

        val filterAdapter = ArrayAdapter(requireContext(), R.layout.simple_spinner_item, filterList)
        filterAdapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.recyclerviewBook.layoutManager = LinearLayoutManager(activity)
        binding.recyclerviewBook.adapter = BookAdapter(bookList)
        binding.spinnerBook.adapter = filterAdapter

        return binding.root
    }

    // 책 검색
    private fun getBookSearch(query: String) {
        val call = bookService.getBookSearch(ttbkey, query)

        call.enqueue(object: Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse(search): " + response.body());
                } else {
                    Log.d("BookFragment", "fail: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("BookFragment", "onFailure: ${t.message}")
            }
        })
    }

    // 책 목록
    private fun getBookList(query: String) {

        val call = bookService.getBookList(ttbkey, "Bestseller", "안드로이드")

        call.enqueue(object: Callback<String> {
            override fun onResponse(
                call: Call<String>,
                response: Response<String>
            ) {
                if (response.isSuccessful) {
                    Log.e(TAG, "onResponse(list): " + response.body());
                    /*
                    response.body()?.books?.forEach{
                        Log.d(TAG, it.toString())
                    }


                    val doc = Jsoup.parse(response.body())
                    val items = doc.select("item")

                    for (item in items) {
                        val title = item.select("title").text()
                        val author = item.select("author").text()
                        val publisher = item.select("publisher").text()
                        val pubdate = item.select("pubdate").text()
                        val cover = item.select("cover").text()
                        val description = item.select("description").text()
                        val isbn = item.select("isbn").text()
                        val priceSales = item.select("priceSales").text()
                        val priceStandard = item.select("priceStandard").text()
                        val mileage = item.select("mileage").text()
                        val link = item.select("link").text()

                        bookList.add(title)
                    }
                     */
                } else {
                    Log.d("BookFragment", "fail: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<String>, t: Throwable) {
                Log.d("BookFragment", "onFailure: ${t.message}")
            }
        })
    }

    companion object{
        private const val TAG = "MainActivity"
    }

}