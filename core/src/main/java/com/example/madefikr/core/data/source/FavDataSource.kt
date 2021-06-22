package com.example.madefikr.core.data.source
import com.example.madefikr.core.coremodel.entity.FavMovieEntity
import com.example.madefikr.core.coremodel.entity.MovieEntity
import com.example.madefikr.core.data.local.dao.CachesDao
import com.example.madefikr.core.data.local.dao.FavoriteDao
import kotlinx.coroutines.flow.Flow


class FavDataSource (
    private val favoriteDao: FavoriteDao
) {

    //Favorite

    fun getAllFavMovieList():Flow<List<FavMovieEntity>>{
        return favoriteDao.getAllFavMovie()
    }

    fun insertFavMovie(movie: FavMovieEntity) {
        favoriteDao.insertFavMovie(movie)
    }

    fun deleteFavMovie(movie:FavMovieEntity){
        favoriteDao.deleteFavMovie(movie)
    }

}