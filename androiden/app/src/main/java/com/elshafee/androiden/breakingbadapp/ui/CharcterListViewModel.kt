package com.elshafee.androiden.breakingbadapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elshafee.androiden.breakingbadapp.repository.CharacterRepository
import kotlinx.coroutines.launch

class CharcterListViewModel(private val characterRepository: CharacterRepository): ViewModel(){

    val characteList = characterRepository.charcters

    fun refreshDataFromRepository(){
        viewModelScope.launch {
            characterRepository.refreshCharcter()
        }
    }
}