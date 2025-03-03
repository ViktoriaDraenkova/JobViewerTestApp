package com.practicum.data.api

import com.practicum.domain.models.VacancyResponse
import retrofit2.http.GET

interface VacancyApi{
    @GET("/uc?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun get(): VacancyResponse
}