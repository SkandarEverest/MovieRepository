package com.example.madefikr.core.di

import androidx.room.Room
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import com.example.madefikr.core.data.local.Database
import com.example.madefikr.core.data.remote.api.Api
import com.example.madefikr.core.data.repository.CachesRepository
import com.example.madefikr.core.data.repository.FavoriteRepository
import com.example.madefikr.core.data.source.FavDataSource
import com.example.madefikr.core.data.source.LocalDataSource
import com.example.madefikr.core.data.source.RemoteDataSource
import com.example.madefikr.core.domain.repository.ICachesRepository
import com.example.madefikr.core.domain.repository.IFavoriteRepository
import com.example.madefikr.core.util.AppExecutors
import com.example.madefikr.core.util.Constants.Companion.BASE_URL
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner


val networkModule = module {
    val hostname = "*.themoviedb.org"
    val certificatePinner = CertificatePinner.Builder()
        .add(hostname, "sha256/+vqZVAzTqUP8BGkfl88yU7SQ3C8J2uNEa55B7RZjEg0=")
        .add(hostname, "sha256/JSMzqOOrtyOT1kmau6zKhgT676hGgczD5VMdRMyJZFA=")
        .add(hostname, "sha256/++MBgDH5WGvL9Bcn5Be30cRcL0f5O+NyoXuWtQdX1aI=")
        .build()
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(Api::class.java)
    }
}

val databaseModule = module {
    val passphrase: ByteArray = SQLiteDatabase.getBytes("dicoding".toCharArray())
    val factory = SupportFactory(passphrase)
    factory { get<Database>().cachesDao() }
    factory { get<Database>().favoriteDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            Database::class.java,
            "fikr.db"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}


val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { FavDataSource(get()) }
    factory { AppExecutors() }
    single<ICachesRepository> {
        CachesRepository(
            get(),
            get(),
        )
    }
    single<IFavoriteRepository> {
        FavoriteRepository(
            get(),
            get(),

            )
    }
}