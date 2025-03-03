package com.practicum.domain.models

data class VacancyResponse(
    val offers: List<Offer>,
    val vacancies: List<Vacancy>
)

data class Offer(
    val id: String?,
    val title: String,
    val link: String,
    val button: ButtonRise?
)

data class ButtonRise(
    val text: String,
)