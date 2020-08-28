package com.acn.jokesapp.viewmodels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acn.jokesapp.data.model.JokeResponse
import com.acn.jokesapp.data.remote.JokesRetrofitBuilder
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class JokesViewModel : ViewModel() {
    val jokes = MutableLiveData<JokeResponse>()
    val isError = MutableLiveData<Boolean>(false)
    private val apiService by lazy { JokesRetrofitBuilder.apiService }

    fun getJokes(count: Int) {
        println(count)
        val a = apiService.getJokes(count)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                jokes.value = it
                isError.value = false
                Log.e("Main", it.joke?.size.toString())
            }, {
                isError.value = true
                Log.e("Main", "onError")
                it.printStackTrace()
            })
    }
}