package com.dror.myapplication.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.dror.myapplication.repository.Repository

class NumbersViewModel(app: Application): AndroidViewModel(app) {
    private val repository = Repository()

    init {
        getNumbers()
    }

    fun getNumbers(): MutableLiveData<List<Int>?> {
        return repository.getNumbers()
    }

    fun getLoading(): MutableLiveData<Boolean> {
        return repository.getLoading()
    }

    fun getError(): MutableLiveData<String?> {
        return repository.getError()
    }
}