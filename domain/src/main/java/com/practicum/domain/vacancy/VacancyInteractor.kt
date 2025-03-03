package com.practicum.domain.vacancy

import com.practicum.domain.models.VacancyResponse
import kotlinx.coroutines.flow.Flow

interface VacancyInteractor {
    suspend fun getVacancies(): Flow<Result<VacancyResponse>>

}