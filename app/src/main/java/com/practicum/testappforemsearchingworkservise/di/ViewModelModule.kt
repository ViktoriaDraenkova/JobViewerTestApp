package com.practicum.testappforemsearchingworkservise.di

import com.practicum.testappforemsearchingworkservise.presentation.favourites.FavouritesViewModel
import com.practicum.testappforemsearchingworkservise.presentation.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        SearchViewModel(get(), get())
    }
    viewModel {
        FavouritesViewModel(get())
    }
}