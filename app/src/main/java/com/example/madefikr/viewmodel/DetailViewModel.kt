package com.example.madefikr.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.madefikr.core.domain.model.MovieDomain
import com.example.madefikr.core.domain.usecase.FavoriteUseCase

class DetailViewModel (
    private val usecase: FavoriteUseCase
) : ViewModel(){

    val favMovieList = usecase.getFavoriteMovie().asLiveData()


    fun insertFavMovie(movie: MovieDomain) {
        usecase.insertFavMovie(movie)
    }


    fun deleteFavMovie(movie:MovieDomain) {
        usecase.deleteFavMovie(movie)
    }


}