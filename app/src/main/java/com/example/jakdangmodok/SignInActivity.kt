package com.example.jakdangmodok

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.ActivityResultCallback
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.lifecycleScope
import com.example.jakdangmodok.databinding.ActivitySignInBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory

class SignInActivity : AppCompatActivity() {

    private val binding by lazy { ActivitySignInBinding.inflate(layoutInflater) }

    private val firebaseAuth by lazy { FirebaseAuth.getInstance() }
    private val googleSignInClient: GoogleSignInClient by lazy { getGoogleClient() }
    private lateinit var googleAuthLauncher: ActivityResultLauncher<Intent>

    private var tokenId: String? = null
    private var email: String? = null

    private val gson = GsonBuilder().setLenient().create()
    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl("http://10.0.2.2:4000/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val authService = retrofit.create(AuthService::class.java)

    private val retrofit2: Retrofit = Retrofit.Builder()
        .baseUrl("https://www.googleapis.com/")
        .addConverterFactory(ScalarsConverterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
    val loginService = retrofit2.create(LoginService::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        setGoogleAuthLauncher()
        addClickListeners()
    }

    private fun addClickListeners() {
        binding.llSignIn.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                requestGoogleLogin()
            }
        }
    }

    private fun requestGoogleLogin() {
        googleSignInClient.signOut()
        val signInIntent = googleSignInClient.signInIntent
        googleAuthLauncher.launch(signInIntent)
    }

    private fun getGoogleClient(): GoogleSignInClient {
        val googleSignInOption = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.web_client_id))
            .requestServerAuthCode(getString(R.string.web_client_id))
            .requestEmail()
            .build()

        return GoogleSignIn.getClient(this@SignInActivity, googleSignInOption)
    }

    private fun setGoogleAuthLauncher() {
        googleAuthLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult(), ActivityResultCallback { result ->
                Log.e(TAG, "resultCode : ${result.resultCode}")
                Log.e(TAG, "result : $result")

                if (result.resultCode == RESULT_OK) {
                    val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)

                    try {
                        val account = task.getResult(ApiException::class.java)
                        tokenId = account.idToken

                        if (tokenId != null && tokenId != "") {
                            val credential: AuthCredential = GoogleAuthProvider.getCredential(account.idToken, null)
                            firebaseAuth.signInWithCredential(credential)
                                .addOnCompleteListener {
                                    if (firebaseAuth.currentUser != null) {
                                        val user: FirebaseUser = firebaseAuth.currentUser!!
                                        email = user.email.toString()
                                        Log.e(TAG, "email : $email")

                                        val googleSignInToken = account.idToken ?: ""
                                        if (googleSignInToken != "") {
                                            Log.e(TAG, "googleSignInToken : $googleSignInToken")
                                        } else {
                                            Log.e(TAG, "googleSignInToken이 null")
                                        }

                                        lifecycleScope.launch {
                                            val responseLogin = loginService.loginGoogle(
                                                "authorization_code",
                                                getString(R.string.web_client_id),
                                                getString(R.string.web_client_secret),
                                                account.serverAuthCode!!
                                            ).body()!!

                                            val accessToken = responseLogin.access_token
                                            val refreshToken = responseLogin.refresh_token
                                            Log.e(TAG, "responseLogin : $responseLogin \n access: $accessToken \n refresh: $refreshToken")

                                            authService.createUser(TokenRequest(accessToken, refreshToken)).enqueue(object : Callback<String> {
                                                override fun onResponse(call: Call<String>, response: Response<String>) {
                                                    if (response.body() == "true") {
                                                        Log.e(TAG, "onResponse : ${response.body()}")
                                                        moveMainActivity(accessToken)
                                                    } else {
                                                        Log.e(TAG, "onResponse : ${response.body()}")
                                                        moveSignUpActivity(accessToken)
                                                    }
                                                }

                                                override fun onFailure(call: Call<String>, t: Throwable) {
                                                    Log.e(TAG, "onFailure : ${t.message}")
                                                }
                                            })
                                        }
                                    }
                                }
                        }
                    }   catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
        )
    }

    private fun moveSignUpActivity(accessToken: String) {
        val intent = Intent(this, SignUpActivity::class.java)
        intent.putExtra("accessToken", accessToken)
        startActivity(intent)
        finish()
    }

    private fun moveMainActivity(accessToken: String) {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("accessToken", accessToken)
        startActivity(intent)
        finish()
    }

    companion object {
        private const val TAG = "SignInActivity"
    }
}