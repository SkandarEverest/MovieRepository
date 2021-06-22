package com.example.madefikr.core.domain.usecase

import com.example.madefikr.core.domain.model.MovieDomain
import com.example.madefikr.core.util.vo.Resource
import kotlinx.coroutines.flow.Flow

interface CacheUseCase {
    fun getMovieList(): Flow<Resource<List<MovieDomain>>>

}