package com.example.madefikr.di

import com.example.madefikr.viewmodel.DetailViewModel
import com.example.madefikr.core.domain.usecase.CacheInteractor
import com.example.madefikr.core.domain.usecase.CacheUseCase
import com.example.madefikr.core.domain.usecase.FavoriteInteractor
import com.example.madefikr.core.domain.usecase.FavoriteUseCase
import com.example.madefikr.viewmodel.MainViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val useCaseModule = module {
    factory<CacheUseCase> { CacheInteractor(get()) }
    factory<FavoriteUseCase> { FavoriteInteractor(get()) }
}

val viewModelModule = module {
    viewModel { MainViewModel(get()) }
    viewModel { DetailViewModel(get()) }
}