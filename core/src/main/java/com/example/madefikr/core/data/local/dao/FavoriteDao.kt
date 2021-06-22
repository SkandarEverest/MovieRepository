package com.example.madefikr.core.data.local.dao

import androidx.room.*
import com.example.madefikr.core.coremodel.entity.FavMovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface FavoriteDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavMovie(movieEntity: FavMovieEntity)

    @Query("SELECT * FROM fav_movie_table ORDER BY id ASC")
    fun getAllFavMovie(): Flow<List<FavMovieEntity>>

    @Delete
    fun deleteFavMovie(movieEntity: FavMovieEntity)

}