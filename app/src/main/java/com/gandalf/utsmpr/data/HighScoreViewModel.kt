package com.gandalf.utsmpr.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HighScoreViewModel(application: Application): AndroidViewModel(application) {

    private val readAllData: LiveData<List<HighScore>>
    private val repository: HighScoreRepo

    init {
        val userDao = HighscoreDatabase.getDatabase(application).highScoreDao()
        repository = HighScoreRepo(userDao)
        readAllData = repository.readAllData
    }

    fun addUser(user: HighScore){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addUser(user)
        }
    }
}