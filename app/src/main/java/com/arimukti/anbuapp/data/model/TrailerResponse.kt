package com.arimukti.anbuapp.data.model


import com.google.gson.annotations.SerializedName

data class TrailerResponse(
    @SerializedName("id")
    val id: Int, // 508943
    @SerializedName("results")
    val results: List<ResultX>

)