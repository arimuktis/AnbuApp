package com.example.anbuapp.data.remote

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

//@Parcelize
data class GenreResponse(
    @SerializedName("genres")
    val genres: List<GenreX>
)