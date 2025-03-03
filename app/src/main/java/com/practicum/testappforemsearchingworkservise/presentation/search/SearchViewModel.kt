package com.practicum.testappforemsearchingworkservise.presentation.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.practicum.domain.vacancy.VacancyInteractor
import com.practicum.testappforemsearchingworkservise.ui.videos.VacancyScreenState
import kotlinx.coroutines.launch

class SearchViewModel(private val vacancyInteractor: VacancyInteractor) : ViewModel() {
    private val stateLiveData = MutableLiveData<VacancyScreenState>()
    fun getStateLiveData(): LiveData<VacancyScreenState> = stateLiveData

    fun getVacancies() {
        stateLiveData.value = VacancyScreenState.Loading
        viewModelScope.launch {
            vacancyInteractor.getVacancies().collect {
                if (it.isSuccess) {
                    stateLiveData.value = VacancyScreenState.Content(it.getOrThrow())
                } else {
                    stateLiveData.value = VacancyScreenState.Error
                }
            }
        }
    }
}