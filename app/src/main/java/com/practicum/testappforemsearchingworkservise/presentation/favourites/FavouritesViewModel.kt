package com.practicum.testappforemsearchingworkservise.presentation.favourites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.domain.favourites.FavouritesInteractor
import com.practicum.domain.models.Vacancy

class FavouritesViewModel(private val favouritesInteractor: FavouritesInteractor) : ViewModel() {
    private val favouritesLiveData = MutableLiveData<List<Vacancy>>()
    fun getStateLiveData(): LiveData<List<Vacancy>> = favouritesLiveData

    fun getFavourites() {
        favouritesLiveData.value = favouritesInteractor.getFavourites()
    }

    fun addToFavourites(vacancy: Vacancy) {
        favouritesInteractor.addToFavourites(vacancy)
    }

    fun deleteFromFavourites(vacancy: Vacancy) {
        favouritesInteractor.switchFavourite(vacancy)
        getFavourites()
    }
}