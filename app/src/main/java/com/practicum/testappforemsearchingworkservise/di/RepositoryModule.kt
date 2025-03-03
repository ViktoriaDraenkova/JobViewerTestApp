package com.practicum.testappforemsearchingworkservise.di

import com.practicum.data.vacancy.VacancyRepositoryImpl
import com.practicum.domain.vacancy.VacancyRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<VacancyRepository> {
        VacancyRepositoryImpl(get())
    }
}