package com.practicum.testappforemsearchingworkservise.di


import com.practicum.data.api.VacancyApi
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val dataModule = module {
    single<VacancyApi> {
        Retrofit.Builder()
            .baseUrl("https://drive.usercontent.google.com/u/0/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(VacancyApi::class.java)
    }
}