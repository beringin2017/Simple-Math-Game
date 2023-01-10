package com.gandalf.utsmpr

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface HighScoreDao {

    @Query("SELECT * from highscore")
    fun getHighScores() : Flow<List<HighScore>>

    @Insert
    fun insertHighScore(vararg highScore: HighScore)
}