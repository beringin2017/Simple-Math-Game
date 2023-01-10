package com.gandalf.utsmpr.data

import androidx.lifecycle.LiveData

class HighScoreRepo(private val highScoreDao: HighScoreDao) {

    val readAllData: LiveData<List<HighScore>> = highScoreDao.readAllData()

    suspend fun addUser(user: HighScore){
        highScoreDao.addUser(user)

    }
}