package com.acn.jokesapp.data.remote

import com.acn.jokesapp.data.model.JokeResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface JokesApiService {
    @GET("jokes/random/{count}?escape=javascript")
    fun getJokes(@Path("count") count: Int): Single<JokeResponse>
}