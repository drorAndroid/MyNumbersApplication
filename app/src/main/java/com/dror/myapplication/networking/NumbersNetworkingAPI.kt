package com.dror.mygallery.networking

import retrofit2.Call
import retrofit2.http.GET


interface NumbersNetworkingAPI {
    @GET("raw/8wJzytQX")
    fun getNumbers(): Call<NumbersResponse?>?


}