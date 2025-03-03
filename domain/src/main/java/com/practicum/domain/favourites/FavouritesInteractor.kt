package com.practicum.domain.favourites

import com.practicum.domain.models.Vacancy

interface FavouritesInteractor {
    fun getFavourites(): List<Vacancy>
    fun addToFavourites(vacancy: Vacancy)
    fun deleteFromFavourites(vacancy: Vacancy)
    fun switchFavourite(vacancy: Vacancy)

}