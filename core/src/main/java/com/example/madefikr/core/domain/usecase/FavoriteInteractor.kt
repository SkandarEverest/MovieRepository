package com.example.madefikr.core.domain.usecase

import com.example.madefikr.core.domain.model.MovieDomain
import com.example.madefikr.core.domain.repository.ICachesRepository
import com.example.madefikr.core.domain.repository.IFavoriteRepository
import com.example.madefikr.core.util.vo.Resource
import kotlinx.coroutines.flow.Flow

class FavoriteInteractor(private val repository: IFavoriteRepository) : FavoriteUseCase {
    override fun getFavoriteMovie(): Flow<List<MovieDomain>> {
        return repository.getFavoriteMovie()
    }

    override fun insertFavMovie(fav: MovieDomain) {
        repository.insertFavMovie(fav)
    }

    override fun deleteFavMovie(fav: MovieDomain) {
        repository.deleteFavMovie(fav)
    }
}