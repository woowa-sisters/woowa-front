package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jakdangmodok.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignUpBinding.inflate(layoutInflater) }
    private val transaction = supportFragmentManager.beginTransaction()
    private val continueStr = R.string.continue_button.toString()
    private val completeStr = R.string.complete_button.toString()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setFragment()
        addClickListener()
    }

    private fun setFragment() {
        transaction.replace(R.id.sign_up_container, UsernameFragment())
        transaction.commit()
    }

    private fun addClickListener() {
        binding.btnContinueSignUp.setOnClickListener {
            when (binding.btnContinueSignUp.text.toString()) {
                continueStr -> {
                    transaction.replace(R.id.sign_up_container, SelectGenreFragment())
                    transaction.addToBackStack(null)
                    transaction.commit()

                    binding.btnContinueSignUp.text = R.string.complete_button.toString()
                }
                completeStr -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

}