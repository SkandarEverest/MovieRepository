package com.example.madefikr.core.data.source
import com.example.madefikr.core.coremodel.entity.FavMovieEntity
import com.example.madefikr.core.coremodel.entity.MovieEntity
import com.example.madefikr.core.data.local.dao.CachesDao
import com.example.madefikr.core.data.local.dao.FavoriteDao
import kotlinx.coroutines.flow.Flow


class LocalDataSource (
    private val cachesDao: CachesDao
) {
    //Caches
    fun getMovieList(): Flow<List<MovieEntity>> {
        return cachesDao.getMovieList()
    }

    suspend fun insertMovieList(movieEntity: List<MovieEntity>) {
        cachesDao.insertMovieList(movieEntity)
    }


}