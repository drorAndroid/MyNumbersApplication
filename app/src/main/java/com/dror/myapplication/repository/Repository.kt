package com.dror.myapplication.repository

import androidx.lifecycle.MutableLiveData
import com.dror.myapplication.networking.NumbersNetworking
import com.dror.myapplication.networking.NumbersNetworkingAPI
import com.dror.myapplication.networking.NumbersResponse

class Repository {
    private var network: NumbersNetworkingAPI? = null

    val mNumbers: MutableLiveData<List<Int>?> = MutableLiveData()
    val mLoading: MutableLiveData<Boolean> = MutableLiveData()
    val mError: MutableLiveData<String?> = MutableLiveData()

    init {
        mLoading.value = false
        mError.value = null
        network = NumbersNetworking.getRetrofitInstance()?.create(NumbersNetworkingAPI::class.java)
    }
    fun getNumbers(): MutableLiveData<List<Int>?> {
        mLoading.value = true
        network?.getNumbers()?.enqueue(object : retrofit2.Callback<NumbersResponse?> {
            override fun onFailure(call: retrofit2.Call<NumbersResponse?>, t: Throwable) {
                mLoading.value = false
                mError.value = "no internet connection"
            }

            override fun onResponse(
                call: retrofit2.Call<NumbersResponse?>,
                response: retrofit2.Response<NumbersResponse?>
            ) {
                if (response.isSuccessful) {
                    var numbers: MutableList<Int>? = null
                    response.body()?.let {
                        numbers = mutableListOf()
                        val numbersResponse = response.body()?.numbers
                        numbersResponse?.forEach { map ->
                            numbers?.add(map["number"]!!)
                        }

                    }
                    numbers?.sort()
                    mNumbers.value = numbers
                    mError.value = null
                }
                else {
                    when (response.code()) {
                        404 -> mError.value = "not found"
                        500 -> mError.value = "server error"
                        else -> mError.value = "unknown error"
                    }
                }

                mLoading.value = false
            }
        })

        return mNumbers
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return mLoading
    }

    fun getError(): MutableLiveData<String?> {
        return mError
    }
}