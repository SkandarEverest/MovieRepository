package com.example.madefikr.core.data.source
import android.util.Log
import com.example.madefikr.core.coremodel.response.MovieDetail
import com.example.madefikr.core.data.remote.api.Api
import com.example.madefikr.core.util.Constants.Companion.API_KEY
import com.example.madefikr.core.util.vo.ApiResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.Dispatchers


class RemoteDataSource (
    private val remoteApi: Api
) {

    suspend fun getMovieList() : Flow<ApiResponse<List<MovieDetail>>> {
        return flow {
            try {
                val response = remoteApi.getMovieList(API_KEY)
                val dataArray = response.results
                if (dataArray.isNotEmpty()){
                    emit(ApiResponse.Success(response.results))
                } else {
                    emit(ApiResponse.Empty)
                }
            } catch (e : Exception){
                emit(ApiResponse.Error(e.toString()))
                Log.e("RemoteDataSource", e.toString())
            }
        }.flowOn(Dispatchers.IO)
    }
}