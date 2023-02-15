package com.elshafee.androiden.breakingbadapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.elshafee.androiden.breakingbadapp.repository.CharacterRepository

class CharcterListViewModelFactory(private val characterRepository: CharacterRepository) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CharcterListViewModel::class.java)) {
            return CharcterListViewModel(characterRepository) as T
        }
        throw java.lang.IllegalArgumentException("Unknown ViewModel CLass")
    }
}