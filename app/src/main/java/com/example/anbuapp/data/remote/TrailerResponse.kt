package com.example.anbuapp.data.remote


import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("id")
    val id: Int, // 508943
    @SerializedName("results")
    val results: List<ResultX>
)