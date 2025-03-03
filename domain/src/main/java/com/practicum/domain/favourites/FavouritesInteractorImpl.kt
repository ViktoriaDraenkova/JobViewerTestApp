package com.practicum.domain.favourites

import com.practicum.domain.models.Vacancy

class FavouritesInteractorImpl(private val favouritesRepository: FavouritesRepository) :
    FavouritesInteractor {
    override fun getFavourites(): List<Vacancy> {
        return favouritesRepository.getFavourites()
    }

    override fun addToFavourites(vacancy: Vacancy) {
        favouritesRepository.addToFavourites(vacancy)
    }

    override fun deleteFromFavourites(vacancy: Vacancy) {
        favouritesRepository.deleteFromFavourites(vacancy)
    }

    override fun switchFavourite(vacancy: Vacancy) {
        favouritesRepository.switchFavourite(vacancy)
    }
}