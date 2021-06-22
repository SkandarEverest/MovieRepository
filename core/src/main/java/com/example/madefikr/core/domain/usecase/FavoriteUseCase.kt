package com.example.madefikr.core.domain.usecase

import com.example.madefikr.core.domain.model.MovieDomain
import kotlinx.coroutines.flow.Flow

interface FavoriteUseCase {

    fun getFavoriteMovie(): Flow<List<MovieDomain>>
    fun insertFavMovie(fav:MovieDomain)
    fun deleteFavMovie(fav:MovieDomain)

}