package com.practicum.testappforemsearchingworkservise.di

import com.practicum.data.favourites.FavouritesRepositoryImpl
import com.practicum.data.vacancy.VacancyRepositoryImpl
import com.practicum.domain.favourites.FavouritesRepository
import com.practicum.domain.vacancy.VacancyRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<VacancyRepository> {
        VacancyRepositoryImpl(get())
    }

    single<FavouritesRepository> {
        FavouritesRepositoryImpl(get())
    }
}