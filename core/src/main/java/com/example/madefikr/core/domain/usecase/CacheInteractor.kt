package com.example.madefikr.core.domain.usecase

import com.example.madefikr.core.domain.model.MovieDomain
import com.example.madefikr.core.domain.repository.ICachesRepository
import com.example.madefikr.core.util.vo.Resource
import kotlinx.coroutines.flow.Flow

class CacheInteractor(private val repository: ICachesRepository) : CacheUseCase {
    override fun getMovieList(): Flow<Resource<List<MovieDomain>>> {
        return repository.getMovieList()
    }

}