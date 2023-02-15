package com.elshafee.androiden.breakingbadapp

import android.app.Application
import com.elshafee.androiden.breakingbadapp.db.CharacterDatabase
import com.elshafee.androiden.breakingbadapp.repository.CharacterRepository

class BreakingBadApllication :Application() {

    val database by lazy {
        CharacterDatabase.createDatabase(this)
    }

    val characterRepository by lazy{
        CharacterRepository(database.createCharacterDao())
    }
}