package com.arimukti.anbuapp.data.model


data class ReviewResponse(
    val id: Int, // 508943
    val page: Int, // 1
    val results: List<Result>,
    val totalPages: Int, // 1
    val totalResults: Int // 3
)