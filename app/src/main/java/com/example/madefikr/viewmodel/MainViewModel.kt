package com.example.madefikr.viewmodel


import com.example.madefikr.core.domain.usecase.CacheUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData


class MainViewModel (
    usecase:CacheUseCase
) : ViewModel(){


    val movieList = usecase.getMovieList().asLiveData()


}