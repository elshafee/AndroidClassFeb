package com.elshafee.androiden.breakingbadapp.repository

import androidx.lifecycle.LiveData
import com.elshafee.androiden.breakingbadapp.db.CharacterDao
import com.elshafee.androiden.breakingbadapp.model.BreakingBadCharacter
import com.elshafee.androiden.breakingbadapp.sevices.BreakingBadNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepository(private val characterDao: CharacterDao) {

    var charcters: LiveData<List<BreakingBadCharacter>> = characterDao.findAllCharacters()

    suspend fun refreshCharcter() {
        withContext(Dispatchers.IO) {
            val characters = BreakingBadNetwork.serviceApi.getCharacters()
            characterDao.insertAllCharacters(characters)
        }
    }
}