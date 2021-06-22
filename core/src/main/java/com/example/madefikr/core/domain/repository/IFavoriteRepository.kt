package com.example.madefikr.core.domain.repository
import com.example.madefikr.core.domain.model.MovieDomain
import com.example.madefikr.core.util.vo.Resource
import kotlinx.coroutines.flow.Flow

interface IFavoriteRepository {

    fun getFavoriteMovie(): Flow<List<MovieDomain>>
    fun insertFavMovie(fav:MovieDomain)
    fun deleteFavMovie(fav:MovieDomain)

}