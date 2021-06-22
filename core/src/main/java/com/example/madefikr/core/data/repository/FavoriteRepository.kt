package com.example.madefikr.core.data.repository
import com.example.madefikr.core.data.source.FavDataSource
import com.example.madefikr.core.data.source.LocalDataSource
import com.example.madefikr.core.domain.model.MovieDomain
import com.example.madefikr.core.domain.repository.IFavoriteRepository
import com.example.madefikr.core.util.AppExecutors
import com.example.madefikr.core.util.extension.DataMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


class FavoriteRepository (
    private val favDataSource: FavDataSource,
    private val appExecutors: AppExecutors
) : IFavoriteRepository{

    override fun getFavoriteMovie(): Flow<List<MovieDomain>> {
        return favDataSource.getAllFavMovieList().map { data ->
            DataMapper.movieFavEntitytoDomain(data)
        }
    }

    override fun insertFavMovie(fav: MovieDomain) {
        val movie = DataMapper.movieDomaintoFavEntity(fav)
        appExecutors.diskIO().execute {
            favDataSource.insertFavMovie(movie) }
    }

    override fun deleteFavMovie(fav: MovieDomain) {
        val movie = DataMapper.movieDomaintoFavEntity(fav)
        appExecutors.diskIO().execute {
            favDataSource.deleteFavMovie(movie)}
    }


}