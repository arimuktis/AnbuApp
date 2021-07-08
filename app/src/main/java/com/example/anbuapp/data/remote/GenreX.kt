package com.example.anbuapp.data.remote

import com.google.gson.annotations.SerializedName

//@Parcelize
data class GenreX(
    @SerializedName("id")
    val id: Int, // 28
    @SerializedName("name")
    val name: String, // Action
    private var selected: Boolean
){

    @JvmName("setSelected1")
    fun setSelected(selected:Boolean){
        this.selected = selected
    }

    fun isSelected():Boolean{
        return this.selected
    }
}
