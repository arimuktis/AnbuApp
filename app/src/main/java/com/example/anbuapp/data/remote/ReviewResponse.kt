package com.example.anbuapp.data.remote


import com.google.gson.annotations.SerializedName

data class ReviewResponse(
    val id: Int, // 508943
    val page: Int, // 1
    val results: List<Result>,
    val totalPages: Int, // 1
    val totalResults: Int // 3
)