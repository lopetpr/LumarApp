package com.example.lumarapp.auth.data.remote

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("id")
    val userId: String,

    @SerializedName("nombre")
    val name: String,

    @SerializedName("rol")
    val rol: String,

    @SerializedName("token")
    val token: String
)
