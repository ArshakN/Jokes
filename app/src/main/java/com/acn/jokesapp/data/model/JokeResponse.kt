package com.acn.jokesapp.data.model

import com.google.gson.annotations.SerializedName

data class JokeResponse(
    @SerializedName("type")
    val type: String? = null,

    @SerializedName("value")
    val joke: List<JokeItem?>? = null
)

data class JokeItem(
    @SerializedName("id")
    val id: Int? = null,

    @SerializedName("categories")
    val categories: List<Any?>? = null,

    @SerializedName("joke")
    val joke: String? = null
)
