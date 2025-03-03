package com.practicum.domain.models

data class Vacancy(
    val lookingNumber: Int,
    val title: String,
    val address: Address,
    val company: String,
    val experience: Experience,
    val publishedDate: String,
    val isFavourite: Boolean,
)

data class Address(
    val town: String,
)

data class Experience(
    val previewText: String,
)
