package com.example.anbuapp.data.remote


import com.google.gson.annotations.SerializedName

data class AuthorDetails(
    @SerializedName("avatar_path")
    val avatarPath: String, // /https://secure.gravatar.com/avatar/3593437cbd05cebe0a4ee753965a8ad1.jpg
    @SerializedName("name")
    val name: String,
    @SerializedName("rating")
    val rating: Any, // null
    @SerializedName("username")
    val username: String // garethmb
)