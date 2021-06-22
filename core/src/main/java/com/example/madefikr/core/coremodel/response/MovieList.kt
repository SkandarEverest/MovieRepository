package com.example.madefikr.core.coremodel.response

import com.google.gson.annotations.SerializedName

data class MovieList(
    @SerializedName("results")
    val results: List<MovieDetail>,
)