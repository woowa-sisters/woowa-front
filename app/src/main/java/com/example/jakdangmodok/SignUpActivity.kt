package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.jakdangmodok.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val fragments: List<Fragment> by lazy {
        listOf(UsernameFragment(), SelectGenreFragment())
    }

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
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.sign_up_container, fragments[1])
                        .commit()

                    binding.btnSignUp.text = "완료"
                }
                "완료" -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

}