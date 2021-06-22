package com.example.madefikr.core.util.extension

import com.example.madefikr.core.coremodel.entity.FavMovieEntity
import com.example.madefikr.core.coremodel.entity.MovieEntity
import com.example.madefikr.core.coremodel.response.MovieDetail
import com.example.madefikr.core.domain.model.MovieDomain

object DataMapper {
    fun movieDetailtoEntity(response: MovieDetail): MovieEntity =
        MovieEntity(
            id = response.id,
            backdrop_path = response.backdrop_path,
            original_language = response.original_language,
            original_title = response.original_title,
            overview = response.overview,
            popularity = response.popularity,
            poster_path = response.poster_path,
            vote_average = response.vote_average,
            vote_count = response.vote_count
        )


    fun movieDomaintoFavEntity(response: MovieDomain): FavMovieEntity =
        FavMovieEntity(
            id = response.id,
            backdrop_path = response.backdrop_path,
            original_language = response.original_language,
            original_title = response.original_title,
            overview = response.overview,
            popularity = response.popularity,
            poster_path = response.poster_path,
            vote_average = response.vote_average,
            vote_count = response.vote_count
        )


    fun movieEntitytoDomain(data:List<MovieEntity>):List<MovieDomain> =
        data.map{
            MovieDomain(
                id = it.id,
                backdrop_path = it.backdrop_path,
                original_language = it.original_language,
                original_title = it.original_title,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                vote_average = it.vote_average,
                vote_count = it.vote_count
            )
        }

    fun movieFavEntitytoDomain(data:List<FavMovieEntity>):List<MovieDomain> =
        data.map{
            MovieDomain(
                id = it.id,
                backdrop_path = it.backdrop_path,
                original_language = it.original_language,
                original_title = it.original_title,
                overview = it.overview,
                popularity = it.popularity,
                poster_path = it.poster_path,
                vote_average = it.vote_average,
                vote_count = it.vote_count
            )
        }
}
