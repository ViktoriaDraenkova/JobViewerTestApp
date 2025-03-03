package com.practicum.data.favourites

import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.practicum.domain.favourites.FavouritesRepository
import com.practicum.domain.models.Vacancy

class FavouritesRepositoryImpl(private val prefs: SharedPreferences): FavouritesRepository {
    override fun getFavourites(): List<Vacancy> {
        val jsonStr = prefs.getString(KEY_FAVOURITES, "[]")
        val listType = object : TypeToken<List<Vacancy>>() {}.type
        return Gson().fromJson(jsonStr, listType)
    }

    override fun addToFavourites(vacancy: Vacancy) {
        val favourites = getFavourites()
        if (vacancy in favourites) {
            return
        }
        prefs.edit().putString(KEY_FAVOURITES, Gson().toJson(favourites.plus(vacancy))).apply()
    }

    override fun deleteFromFavourites(vacancy: Vacancy) {
        val favourites = getFavourites()
        if (vacancy in favourites) {
            prefs.edit().putString(KEY_FAVOURITES, Gson().toJson(favourites.minus(vacancy))).apply()
        }
    }

    override fun switchFavourite(vacancy: Vacancy) {
        val favourites = getFavourites()
        val found = favourites.find { it.id == vacancy.id }
        if (found != null) {
            prefs.edit().putString(KEY_FAVOURITES, Gson().toJson(favourites.minus(found))).apply()
        } else {
            prefs.edit().putString(KEY_FAVOURITES, Gson().toJson(favourites.plus(vacancy))).apply()
        }
    }

    companion object {
        const val KEY_FAVOURITES = "favourites"
    }
}