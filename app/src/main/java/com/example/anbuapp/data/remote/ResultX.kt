package com.example.anbuapp.data.remote


import com.google.gson.annotations.SerializedName

data class ResultX(
    @SerializedName("id")
    val id: String, // 6037aeb915959f003e87c31e
    @SerializedName("iso_3166_1")
    val iso31661: String, // US
    @SerializedName("iso_639_1")
    val iso6391: String, // en
    @SerializedName("key")
    val key: String, // YdAIBlPVe9s
    @SerializedName("name")
    val name: String, // Teaser Trailer
    @SerializedName("site")
    val site: String, // YouTube
    @SerializedName("size")
    val size: Int, // 1080
    @SerializedName("type")
    val type: String // Trailer
)