package com.example.madefikr.core.data.local.dao

import androidx.room.*
import com.example.madefikr.core.coremodel.entity.MovieEntity
import kotlinx.coroutines.flow.Flow


@Dao
interface CachesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieList(movieEntity: List<MovieEntity>)


    @Query("SELECT * FROM movie_table ORDER BY id ASC")
    fun getMovieList(): Flow<List<MovieEntity>>


}