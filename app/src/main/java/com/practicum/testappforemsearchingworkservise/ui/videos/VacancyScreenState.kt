package com.practicum.testappforemsearchingworkservise.ui.videos

import com.practicum.domain.models.VacancyResponse

sealed interface VacancyScreenState{
    data class Content(
        val vacancyResponse: VacancyResponse
    ): VacancyScreenState

    data object Error: VacancyScreenState
    data object Loading: VacancyScreenState
}