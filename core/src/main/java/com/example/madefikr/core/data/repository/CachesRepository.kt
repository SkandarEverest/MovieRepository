package com.example.madefikr.core.data.repository

import com.example.madefikr.core.data.remote.NetworkBoundResource
import com.example.madefikr.core.domain.model.MovieDomain
import com.example.madefikr.core.coremodel.entity.MovieEntity
import com.example.madefikr.core.coremodel.response.MovieDetail
import com.example.madefikr.core.data.source.LocalDataSource
import com.example.madefikr.core.data.source.RemoteDataSource
import com.example.madefikr.core.domain.repository.ICachesRepository
import com.example.madefikr.core.util.extension.DataMapper
import com.example.madefikr.core.util.vo.ApiResponse
import com.example.madefikr.core.util.vo.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CachesRepository (
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) :ICachesRepository {
    override fun getMovieList(): Flow<Resource<List<MovieDomain>>> =
        object : NetworkBoundResource<List<MovieDomain>, List<MovieDetail>>() {

            override fun loadFromDB(): Flow<List<MovieDomain>> {
                return localDataSource.getMovieList().map { data ->
                    DataMapper.movieEntitytoDomain(data)
                }
            }

            override fun shouldFetch(data: List<MovieDomain>?): Boolean =
                data == null || data.isEmpty()
//                true // ganti dengan true jika ingin selalu mengambil data dari internet

            override suspend fun createCall(): Flow<ApiResponse<List<MovieDetail>>> =
                remoteDataSource.getMovieList()

            override suspend fun saveCallResult(data: List<MovieDetail>) {
                val moviesEntity = data.map{DataMapper.movieDetailtoEntity(it)}
                localDataSource.insertMovieList(moviesEntity)
            }
        }.asFlow()



}