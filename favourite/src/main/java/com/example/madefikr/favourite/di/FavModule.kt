package com.example.madefikr.favourite.di

import com.example.madefikr.favourite.viewmodel.FavViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val favViewModelModule = module {
    viewModel { FavViewModel(get()) }
}