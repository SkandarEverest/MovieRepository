package com.example.madefikr.core.data.local

import androidx.room.RoomDatabase
import com.example.madefikr.core.coremodel.entity.MovieEntity
import com.example.madefikr.core.coremodel.entity.FavMovieEntity
import com.example.madefikr.core.data.local.dao.CachesDao
import com.example.madefikr.core.data.local.dao.FavoriteDao
import androidx.room.Database


@Database(
    entities = [MovieEntity::class, FavMovieEntity::class],
    version = 5,
    exportSchema = false
)

abstract class Database: RoomDatabase() {

    abstract fun cachesDao(): CachesDao
    abstract fun favoriteDao(): FavoriteDao

}