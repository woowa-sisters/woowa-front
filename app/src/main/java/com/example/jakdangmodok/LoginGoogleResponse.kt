package com.example.jakdangmodok

data class LoginGoogleResponse(
    var access_token: String = "",
    var expires_in: Int = 0,
    var refresh_token: String = "",
    var scope: String = "",
    var token_type: String = "",
    var id_token: String = "",
)