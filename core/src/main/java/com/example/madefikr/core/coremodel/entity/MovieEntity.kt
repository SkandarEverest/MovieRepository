package com.example.madefikr.core.coremodel.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.madefikr.core.util.Constants.Companion.MOVIE_TABLE

@Entity(tableName = MOVIE_TABLE)
data class MovieEntity(
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