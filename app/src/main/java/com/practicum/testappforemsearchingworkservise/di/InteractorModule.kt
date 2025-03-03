package com.practicum.testappforemsearchingworkservise.di

import com.practicum.domain.favourites.FavouritesInteractor
import com.practicum.domain.favourites.FavouritesInteractorImpl
import com.practicum.domain.vacancy.VacancyInteractor
import com.practicum.domain.vacancy.VacancyInteractorImpl
import org.koin.dsl.module

val interactorModule = module {
    single<VacancyInteractor> {
        VacancyInteractorImpl(get())
    }
    single<FavouritesInteractor> {
        FavouritesInteractorImpl(get())
    }
}