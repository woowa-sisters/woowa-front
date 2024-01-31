package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jakdangmodok.databinding.ActivityProfileBinding

class ProfileActivity : AppCompatActivity() {

    private val binding by lazy { ActivityProfileBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
        binding.buttonMyGroup.setOnClickListener {
            val intent = Intent(this, MyGroupActivity::class.java)
            startActivity(intent)
        }
    }

}