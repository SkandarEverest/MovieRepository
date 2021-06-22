package com.example.madefikr.core.data.remote.api

import com.example.madefikr.core.coremodel.response.MovieList
import retrofit2.http.*

interface Api {

    // GET Movies List
    @GET("movie/now_playing")
    suspend fun getMovieList(
        @Query("api_key") api_key: String
    ): MovieList

}