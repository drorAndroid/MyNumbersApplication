package com.dror.myapplication.networking

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NumbersNetworking {
    companion object {
        private val BASE_URL = "https://pastebin.com/"

        private var retrofit: Retrofit? = null

        fun getRetrofitInstance(): Retrofit? {
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return retrofit
        }
    }
}