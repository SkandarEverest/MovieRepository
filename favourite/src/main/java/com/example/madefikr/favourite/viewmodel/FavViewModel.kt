package com.example.madefikr.favourite.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.madefikr.core.domain.usecase.FavoriteUseCase


class FavViewModel (
    usecase: FavoriteUseCase
) : ViewModel(){

    val favMovieList = usecase.getFavoriteMovie().asLiveData()


}