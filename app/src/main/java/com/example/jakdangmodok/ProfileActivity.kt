package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import androidx.lifecycle.lifecycleScope
import com.example.jakdangmodok.databinding.ActivityProfileBinding
import com.google.gson.GsonBuilder
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class ProfileActivity : AppCompatActivity() {

    private val binding by lazy { ActivityProfileBinding.inflate(layoutInflater) }

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

        setSupportActionBar(binding.toolbarProfile)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        buttonClick()
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

        binding.buttonLogout.setOnClickListener {
            Log.e("dsfsfsfdf", intent.getStringExtra("accessToken")!!)
            authService.logout("Bearer " + intent.getStringExtra("accessToken")!!).enqueue(object : retrofit2.Callback<String> {
                override fun onResponse(call: retrofit2.Call<String>, response: retrofit2.Response<String>) {
                    if (response.isSuccessful) {
                        Log.e("sdfsf", "onResponse : ${response.body()}")
                    } else {
                        Log.e("sdfsf", "onResponseFail : ${response.body()}")
                    }
                }

                override fun onFailure(call: retrofit2.Call<String>, t: Throwable) {
                    Log.e("sdfsf", "t.message.toString()")
                }
            })
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