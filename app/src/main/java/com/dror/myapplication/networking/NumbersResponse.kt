package com.dror.mygallery.networking

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class NumbersResponse {
    @SerializedName("numbers")
    @Expose
    var numbers: List<HashMap<String, Int>>? = null
}