package com.arimukti.anbuapp.data.model

import com.google.gson.annotations.SerializedName

//@Parcelize
data class GenreResponse(
    @SerializedName("genres")
    val genres: List<GenreX>
)