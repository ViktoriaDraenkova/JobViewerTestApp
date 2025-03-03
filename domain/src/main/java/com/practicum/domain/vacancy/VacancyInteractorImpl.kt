package com.practicum.domain.vacancy

import com.practicum.domain.models.VacancyResponse
import kotlinx.coroutines.flow.Flow

class VacancyInteractorImpl(private val vacancyRepository: VacancyRepository) : VacancyInteractor {
    override suspend fun getVacancies(): Flow<Result<VacancyResponse>> {
        return vacancyRepository.getVacancies()
    }
}