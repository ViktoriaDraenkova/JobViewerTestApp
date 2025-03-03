package com.practicum.data.vacancy

import com.practicum.data.api.VacancyApi
import com.practicum.domain.models.VacancyResponse
import com.practicum.domain.vacancy.VacancyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class VacancyRepositoryImpl(private val vacancyApi: VacancyApi) : VacancyRepository {
    override suspend fun getVacancies(): Flow<Result<VacancyResponse>> = flow {
        try {
            emit(Result.success(vacancyApi.get()))
        } catch (err: Throwable) {
            emit(Result.failure(err))
        }
    }
}