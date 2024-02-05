package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.jakdangmodok.databinding.ActivitySignUpBinding
import com.google.firebase.auth.UserInfo
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class SignUpActivity : AppCompatActivity() {

    val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val fragments: List<Fragment> by lazy {
        listOf(UsernameFragment(), SelectGenreFragment())
    }
    private lateinit var nickname: String
    val genreList = listOf("소설", "시/에세이", "경제/경영", "자기계발", "인문", "역사/문화", "사회", "과학", "예술/대중문화", "종교", "외국어", "기술/공학", "컴퓨터/IT", "취미/스포츠", "건강/다이어트", "가정/육아", "요리", "여행", "교재/수험서", "커리어/수험서", "청소년", "어린이", "만화", "잡지", "해외도서", "오디오북")

    private val gson = GsonBuilder().setLenient().create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:4000/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val authService = retrofit.create(AuthService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment()
        addClickListener()
    }

    private fun setFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.sign_up_container, fragments[0])
            .commit()
    }

    private fun addClickListener() {
        binding.btnSignUp.setOnClickListener {
            when (binding.btnSignUp.text.toString()) {
                "계속" -> {
                    val edtNickname = findViewById<EditText>(R.id.edt_username)
                    if (edtNickname.text.toString() == "") {
                        edtNickname.error = "닉네임을 입력해주세요"
                        return@setOnClickListener
                    }
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.sign_up_container, fragments[1])
                        .commit()

                    binding.btnSignUp.text = "완료"
                }
                "완료" -> {
                    //genre = (fragments[1] as SelectGenreFragment).getSelectedGenre()

                    var genre = mutableListOf<Genre>()
                    for (i in 0..genreList.size-1) {
                        if (i == 0 || i == 3 || i == 7) {
                            genre.add(Genre(genreList[i], true))
                        } else {
                            genre.add(Genre(genreList[i], false))
                        }
                    }

                    val accessToken = intent.getStringExtra("accessToken")
                    Log.e("SignUpActivity", "accessToken: $accessToken")

                    val userInfo = UserInfoRequest(
                        token = accessToken!!,
                        nickname = nickname,
                        jenre = genre
                    )

                    authService.addUserInfo(userInfo).enqueue(object : Callback<String> {
                        override fun onResponse(call: Call<String>, response: Response<String>) {
                            if (response.isSuccessful) {
                                Log.e("SignUpActivity", "회원가입 성공 ${response.body()}")
                            } else {
                                Log.e("SignUpActivity", "회원가입 실패 ${response.errorBody()?.string()}")
                            }
                        }

                        override fun onFailure(call: Call<String>, t: Throwable) {
                            Log.e("SignUpActivity", "회원가입 실패 ${t.message}}")
                        }
                    })

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

}