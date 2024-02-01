package com.example.jakdangmodok

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jakdangmodok.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class SignInActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


    }

    private fun getGoogleClient(): GoogleSignInClient {
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestServerAuthCode(getString(R.string.google_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(this, googleSignInOption)
    }

}