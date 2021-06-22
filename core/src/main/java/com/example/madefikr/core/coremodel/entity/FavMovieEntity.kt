package com.example.madefikr.core.coremodel.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.madefikr.core.util.Constants.Companion.FAV_MOVIE_TABLE

@Entity(tableName = FAV_MOVIE_TABLE)
data class FavMovieEntity(
    @PrimaryKey
    val id:Int?,
    val backdrop_path: String?,
    val original_language: String?,
    val original_title: String?,
    val overview: String?,
    val popularity: Double?,
    val poster_path: String?,
    val vote_average: Double?,
    val vote_count: Int?
)